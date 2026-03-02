package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // ===============================
    // UC8: LengthUnit ENUM TESTS
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
    void testConvertToBaseUnit_FeetToFeet() {
        assertEquals(5.0,
                LengthUnit.FEET.convertToBaseUnit(5.0),
                EPSILON);
    }

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0,
                LengthUnit.INCH.convertToBaseUnit(12.0),
                EPSILON);
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0,
                LengthUnit.YARDS.convertToBaseUnit(1.0),
                EPSILON);
    }

    @Test
    void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0,
                LengthUnit.CENTIMETERS.convertToBaseUnit(30.48),
                EPSILON);
    }

    // ===============================
    // Convert From Base Unit
    // ===============================

    @Test
    void testConvertFromBaseUnit_FeetToFeet() {
        assertEquals(2.0,
                LengthUnit.FEET.convertFromBaseUnit(2.0),
                EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0,
                LengthUnit.INCH.convertFromBaseUnit(1.0),
                EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0,
                LengthUnit.YARDS.convertFromBaseUnit(3.0),
                EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48,
                LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0),
                EPSILON);
    }

    // ===============================
    // Equality Tests (Refactored)
    // ===============================

    @Test
    void testQuantityLengthRefactored_Equality() {

        QuantityMeasurementApp.Length foot =
                new QuantityMeasurementApp.Length(1.0, LengthUnit.FEET);

        QuantityMeasurementApp.Length inches =
                new QuantityMeasurementApp.Length(12.0, LengthUnit.INCH);

        assertTrue(foot.equals(inches));
    }

    // ===============================
    // ConvertTo Test
    // ===============================

    @Test
    void testQuantityLengthRefactored_ConvertTo() {

        QuantityMeasurementApp.Length foot =
                new QuantityMeasurementApp.Length(1.0, LengthUnit.FEET);

        QuantityMeasurementApp.Length result =
                foot.convertTo(LengthUnit.INCH);

        assertEquals(12.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    // ===============================
    // UC6 Addition Test
    // ===============================

    @Test
    void testQuantityLengthRefactored_Add() {

        QuantityMeasurementApp.Length foot =
                new QuantityMeasurementApp.Length(1.0, LengthUnit.FEET);

        QuantityMeasurementApp.Length inch =
                new QuantityMeasurementApp.Length(12.0, LengthUnit.INCH);

        QuantityMeasurementApp.Length result = foot.add(inch);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    // ===============================
    // UC7 Addition With Target Unit
    // ===============================

    @Test
    void testQuantityLengthRefactored_AddWithTargetUnit() {

        QuantityMeasurementApp.Length foot =
                new QuantityMeasurementApp.Length(1.0, LengthUnit.FEET);

        QuantityMeasurementApp.Length inch =
                new QuantityMeasurementApp.Length(12.0, LengthUnit.INCH);

        QuantityMeasurementApp.Length result =
                foot.add(inch, LengthUnit.YARDS);

        assertEquals(2.0 / 3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    // ===============================
    // Null & Invalid Tests
    // ===============================

    @Test
    void testQuantityLengthRefactored_NullUnit() {

        assertThrows(IllegalArgumentException.class,
                () -> new QuantityMeasurementApp.Length(1.0, null));
    }

    @Test
    void testQuantityLengthRefactored_InvalidValue() {

        assertThrows(IllegalArgumentException.class,
                () -> new QuantityMeasurementApp.Length(Double.NaN, LengthUnit.FEET));
    }

    // ===============================
    // Round Trip Conversion
    // ===============================

    @Test
    void testRoundTripConversion_RefactoredDesign() {

        QuantityMeasurementApp.Length original =
                new QuantityMeasurementApp.Length(5.0, LengthUnit.FEET);

        QuantityMeasurementApp.Length inches =
                original.convertTo(LengthUnit.INCH);

        QuantityMeasurementApp.Length backToFeet =
                inches.convertTo(LengthUnit.FEET);

        assertEquals(original.getValue(),
                backToFeet.getValue(),
                EPSILON);
    }
}