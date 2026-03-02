package com.bridgelabz;

public class QuantityMeasurementApp {

    // ===============================
    // ENUM: Length Units
    // Base Unit = FEET
    // ===============================
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
    }

    // ===============================
    // VALUE OBJECT: Length
    // ===============================
    public static class Length {

        private final double value;
        private final LengthUnit unit;

        private static final double EPSILON = 1e-6;

        // Constructor
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

        // Convert to base unit (feet)
        private double toBaseUnit() {
            return value * unit.getConversionFactor();
        }

        // ===============================
        // UC5: STATIC CONVERT METHOD
        // ===============================
        public static double convert(double value,
                                     LengthUnit source,
                                     LengthUnit target) {

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Value must be finite");

            if (source == null || target == null)
                throw new IllegalArgumentException("Units cannot be null");

            double valueInBase = value * source.getConversionFactor();

            return valueInBase / target.getConversionFactor();
        }

        // Instance convert
        public Length convertTo(LengthUnit target) {
            double convertedValue = convert(this.value, this.unit, target);
            return new Length(convertedValue, target);
        }

        // ===============================
        // UC6: ADDITION METHOD
        // ===============================
        public Length add(Length other) {

            if (other == null)
                throw new IllegalArgumentException("Second operand cannot be null");

            if (!Double.isFinite(other.value))
                throw new IllegalArgumentException("Value must be finite");

            // Convert both to base unit (feet)
            double thisInBase = this.toBaseUnit();
            double otherInBase = other.toBaseUnit();

            // Add in base unit
            double sumInBase = thisInBase + otherInBase;

            // Convert back to unit of FIRST operand
            double resultValue = sumInBase / this.unit.getConversionFactor();

            // Return new Length object (immutability preserved)
            return new Length(resultValue, this.unit);
        }
        // ===============================
// UC7: ADD WITH TARGET UNIT
// ===============================
        public Length add(Length other, LengthUnit targetUnit) {

            if (other == null)
                throw new IllegalArgumentException("Second operand cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
                throw new IllegalArgumentException("Values must be finite");

            // Convert both to base unit (feet)
            double thisInBase = this.toBaseUnit();
            double otherInBase = other.toBaseUnit();

            // Add in base unit
            double sumInBase = thisInBase + otherInBase;

            // Convert to explicitly specified target unit
            double resultValue = sumInBase / targetUnit.getConversionFactor();

            // Return new Length object in target unit
            return new Length(resultValue, targetUnit);
        }

        // Optional static overloaded add
        public static Length add(Length first, Length second) {

            if (first == null || second == null)
                throw new IllegalArgumentException("Operands cannot be null");

            return first.add(second);
        }

        // ===============================
        // EQUALS METHOD
        // ===============================
        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Length other = (Length) obj;

            return Math.abs(this.toBaseUnit()
                    - other.toBaseUnit()) < EPSILON;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }
}