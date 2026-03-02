package com.bridgelabz;

public class QuantityMeasurementApp {

    // Base unit = FEET
    public enum LengthUnit {

        FEET(1.0),

        INCH(1.0 / 12.0),          // 1 inch = 1/12 feet

        YARDS(3.0),               // 1 yard = 3 feet

        CENTIMETERS(0.0328084);   // 1 cm = 0.0328084 feet

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    public static class Length {

        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 1e-6;

        public Length(double value, LengthUnit unit) {

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Value must be finite");

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        // ===============================
        // STATIC CONVERSION METHOD (UC5)
        // ===============================
        public static double convert(double value, LengthUnit source, LengthUnit target) {

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Value must be finite");

            if (source == null || target == null)
                throw new IllegalArgumentException("Units cannot be null");

            // Step 1: Convert to base unit (feet)
            double valueInBase = value * source.getConversionFactor();

            // Step 2: Convert to target unit
            return valueInBase / target.getConversionFactor();
        }

        // Instance method conversion (returns new object)
        public Length convertTo(LengthUnit target) {
            double convertedValue = convert(this.value, this.unit, target);
            return new Length(convertedValue, target);
        }

        // Convert current object to base unit (feet)
        private double toBaseUnit() {
            return value * unit.getConversionFactor();
        }

        // ===============================
        // EQUALS METHOD (WITH EPSILON)
        // ===============================
        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Length other = (Length) obj;

            return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // Optional Main Method (for quick testing)
    public static void main(String[] args) {

        System.out.println("1 foot to inches = " +
                Length.convert(1.0, LengthUnit.FEET, LengthUnit.INCH));

        System.out.println("3 yards to feet = " +
                Length.convert(3.0, LengthUnit.YARDS, LengthUnit.FEET));

        System.out.println("36 inches to yards = " +
                Length.convert(36.0, LengthUnit.INCH, LengthUnit.YARDS));

        System.out.println("2.54 cm to inches = " +
                Length.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCH));
    }
}