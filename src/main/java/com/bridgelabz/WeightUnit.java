package com.bridgelabz;

public enum WeightUnit {

    KILOGRAM(1.0),          // Base unit
    GRAM(0.001),            // 1 g = 0.001 kg
    POUND(0.453592);        // 1 lb ≈ 0.453592 kg

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    // Convert this unit to base (kg)
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    // Convert from base (kg) to this unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}