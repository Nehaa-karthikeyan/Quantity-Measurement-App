package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityWeightTest {

    private static final double EPSILON = 1e-6;

    // ===============================
    // Equality Tests
    // ===============================

    @Test
    void testEquality_KilogramToGram() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);
        assertTrue(kg.equals(g));
    }

    @Test
    void testEquality_KilogramToPound() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight lb = new QuantityWeight(2.20462, WeightUnit.POUND);
        assertTrue(kg.equals(lb));
    }

    @Test
    void testEquality_NullComparison() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertFalse(kg.equals(null));
    }

    // ===============================
    // Conversion Tests
    // ===============================

    @Test
    void testConversion_KgToGram() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight result = kg.convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_PoundToKg() {
        QuantityWeight lb = new QuantityWeight(2.20462, WeightUnit.POUND);
        QuantityWeight result = lb.convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.0, result.getValue(), 1e-3);
    }

    // ===============================
    // Addition Tests
    // ===============================

    @Test
    void testAddition_SameUnit() {
        QuantityWeight one = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight two = new QuantityWeight(2.0, WeightUnit.KILOGRAM);
        QuantityWeight result = one.add(two);
        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);
        QuantityWeight result = kg.add(g);
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTarget() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);
        QuantityWeight result = kg.add(g, WeightUnit.GRAM);
        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_Commutative() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result1 = kg.add(g, WeightUnit.KILOGRAM);
        QuantityWeight result2 = g.add(kg, WeightUnit.KILOGRAM);

        assertEquals(result1.getValue(), result2.getValue(), EPSILON);
    }

    // ===============================
    // Category Incompatibility
    // ===============================

    @Test
    void testWeightVsLength_NotEqual() {

        QuantityWeight weight =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityMeasurementApp.Length length =
                new QuantityMeasurementApp.Length(1.0, LengthUnit.FEET);

        assertFalse(weight.equals(length));
    }
}