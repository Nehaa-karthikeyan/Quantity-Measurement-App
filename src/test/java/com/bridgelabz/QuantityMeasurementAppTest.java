package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // ===============================
    // LengthUnit ENUM TESTS
    // ===============================

    @Test
    void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12.0, LengthUnit.INCH.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0, LengthUnit.YARDS.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(0.0328084, LengthUnit.CENTIMETERS.getConversionFactor(), EPSILON);
    }

    // ===============================
    // Convert To Base Unit
    // ===============================

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0,
                LengthUnit.INCH.convertToBaseUnit(12.0),
                EPSILON);
    }

    // ===============================
    // GENERIC QUANTITY TESTS (UC10)
    // ===============================

    @Test
    void testGenericQuantity_LengthEquality() {

        Quantity<LengthUnit> foot =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCH);

        assertEquals(foot, inches);
    }

    @Test
    void testGenericQuantity_LengthConvertTo() {

        Quantity<LengthUnit> foot =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                foot.convertTo(LengthUnit.INCH);

        assertEquals(12.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    void testGenericQuantity_LengthAddition() {

        Quantity<LengthUnit> foot =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inch =
                new Quantity<>(12.0, LengthUnit.INCH);

        Quantity<LengthUnit> result =
                foot.add(inch);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testGenericQuantity_LengthAdditionWithTargetUnit() {

        Quantity<LengthUnit> foot =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inch =
                new Quantity<>(12.0, LengthUnit.INCH);

        Quantity<LengthUnit> result =
                foot.add(inch, LengthUnit.YARDS);

        assertEquals(2.0 / 3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    void testGenericQuantity_LengthRoundTripConversion() {

        Quantity<LengthUnit> original =
                new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                original.convertTo(LengthUnit.INCH);

        Quantity<LengthUnit> backToFeet =
                inches.convertTo(LengthUnit.FEET);

        assertEquals(original.getValue(),
                backToFeet.getValue(),
                EPSILON);
    }

    // ===============================
    // WEIGHT TESTS (UC9 Preserved via UC10)
    // ===============================

    @Test
    void testGenericQuantity_WeightEquality() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> g =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(kg, g);
    }

    @Test
    void testGenericQuantity_WeightConversion() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result =
                kg.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testGenericQuantity_CrossCategoryPrevention() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(length, weight);
    }

    @Test
    void testGenericQuantity_NullUnit() {

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    void testGenericQuantity_InvalidValue() {

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }
    @Test
    void testEquality_LitreToMillilitre() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> ml =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(litre.equals(ml));
    }
}