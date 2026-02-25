package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testEquality_FeetToFeet_SameValue() {
        QuantityMeasurementApp.Length one =
                new QuantityMeasurementApp.Length(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Length two =
                new QuantityMeasurementApp.Length(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(one.equals(two));
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        QuantityMeasurementApp.Length one =
                new QuantityMeasurementApp.Length(12.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.Length two =
                new QuantityMeasurementApp.Length(12.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(one.equals(two));
    }

    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        QuantityMeasurementApp.Length oneFoot =
                new QuantityMeasurementApp.Length(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Length twelveInches =
                new QuantityMeasurementApp.Length(12.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(oneFoot.equals(twelveInches));
    }

    @Test
    void testEquality_DifferentValue() {
        QuantityMeasurementApp.Length one =
                new QuantityMeasurementApp.Length(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Length two =
                new QuantityMeasurementApp.Length(2.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(one.equals(two));
    }

    @Test
    void testEquality_NullComparison() {
        QuantityMeasurementApp.Length one =
                new QuantityMeasurementApp.Length(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(one.equals(null));
    }

    @Test
    void testEquality_SameReference() {
        QuantityMeasurementApp.Length one =
                new QuantityMeasurementApp.Length(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(one.equals(one));
    }
}