package com.bridgelabz;

public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(0.0328084);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    // Convert value in THIS unit → Base Unit (FEET)
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    // Convert Base Unit (FEET) → THIS unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}