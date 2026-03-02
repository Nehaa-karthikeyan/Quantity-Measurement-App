package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // ===============================
    // UC3 / UC4 EQUALITY TESTS
    // ===============================

    @Test
    void testEquality_YardToYard_SameValue() {

        QuantityMeasurementApp.Length one =
                new QuantityMeasurementApp.Length(1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS);

        QuantityMeasurementApp.Length two =
                new QuantityMeasurementApp.Length(1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS);

        assertTrue(one.equals(two));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {

        QuantityMeasurementApp.Length yard =
                new QuantityMeasurementApp.Length(1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS);

        QuantityMeasurementApp.Length feet =
                new QuantityMeasurementApp.Length(3.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(yard.equals(feet));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {

        QuantityMeasurementApp.Length yard =
                new QuantityMeasurementApp.Length(1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS);

        QuantityMeasurementApp.Length inches =
                new QuantityMeasurementApp.Length(36.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(yard.equals(inches));
    }

    @Test
    void testEquality_CentimeterToInch_EquivalentValue() {

        QuantityMeasurementApp.Length cm =
                new QuantityMeasurementApp.Length(1.0,
                        QuantityMeasurementApp.LengthUnit.CENTIMETERS);

        QuantityMeasurementApp.Length inch =
                new QuantityMeasurementApp.Length(0.393701,
                        QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(cm.equals(inch));
    }

    @Test
    void testEquality_NullComparison() {

        QuantityMeasurementApp.Length yard =
                new QuantityMeasurementApp.Length(1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS);

        assertFalse(yard.equals(null));
    }

    @Test
    void testEquality_SameReference() {

        QuantityMeasurementApp.Length yard =
                new QuantityMeasurementApp.Length(2.0,
                        QuantityMeasurementApp.LengthUnit.YARDS);

        assertTrue(yard.equals(yard));
    }

    // ===============================
    // UC5 CONVERSION TESTS
    // ===============================

    @Test
    void testConversion_FeetToInch() {
        assertEquals(12.0,
                QuantityMeasurementApp.Length.convert(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testConversion_InchToFeet() {
        assertEquals(2.0,
                QuantityMeasurementApp.Length.convert(
                        24.0,
                        QuantityMeasurementApp.LengthUnit.INCH,
                        QuantityMeasurementApp.LengthUnit.FEET),
                EPSILON);
    }

    @Test
    void testConversion_YardsToInch() {
        assertEquals(36.0,
                QuantityMeasurementApp.Length.convert(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {
        assertEquals(0.0,
                QuantityMeasurementApp.Length.convert(
                        0.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        assertEquals(-12.0,
                QuantityMeasurementApp.Length.convert(
                        -1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testConversion_RoundTrip() {

        double value = 5.0;

        double inches = QuantityMeasurementApp.Length.convert(
                value,
                QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.INCH);

        double backToFeet = QuantityMeasurementApp.Length.convert(
                inches,
                QuantityMeasurementApp.LengthUnit.INCH,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(value, backToFeet, EPSILON);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.Length.convert(
                        1.0,
                        null,
                        QuantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    void testConversion_NaN_Throws() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.Length.convert(
                        Double.NaN,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH));
    }
}