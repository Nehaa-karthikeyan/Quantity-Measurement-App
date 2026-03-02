package com.bridgelabz;

public class QuantityMeasurementApp {

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
        // UC5 Convert
        // ===============================
        public static double convert(double value,
                                     LengthUnit source,
                                     LengthUnit target) {

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Value must be finite");

            if (source == null || target == null)
                throw new IllegalArgumentException("Units cannot be null");

            double baseValue = source.convertToBaseUnit(value);
            return target.convertFromBaseUnit(baseValue);
        }

        public Length convertTo(LengthUnit target) {
            double convertedValue = convert(this.value, this.unit, target);
            return new Length(convertedValue, target);
        }

        // ===============================
        // UC6 Add (implicit target)
        // ===============================
        public Length add(Length other) {

            if (other == null)
                throw new IllegalArgumentException("Second operand cannot be null");

            double baseSum =
                    this.unit.convertToBaseUnit(this.value)
                            + other.unit.convertToBaseUnit(other.value);

            double result =
                    this.unit.convertFromBaseUnit(baseSum);

            return new Length(result, this.unit);
        }

        // ===============================
        // UC7 Add (explicit target)
        // ===============================
        public Length add(Length other, LengthUnit targetUnit) {

            if (other == null)
                throw new IllegalArgumentException("Second operand cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double baseSum =
                    this.unit.convertToBaseUnit(this.value)
                            + other.unit.convertToBaseUnit(other.value);

            double result =
                    targetUnit.convertFromBaseUnit(baseSum);

            return new Length(result, targetUnit);
        }

        // ===============================
        // Equality
        // ===============================
        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (!(obj instanceof Length))
                return false;

            Length other = (Length) obj;

            double thisBase = unit.convertToBaseUnit(value);
            double otherBase = other.unit.convertToBaseUnit(other.value);

            return Math.abs(thisBase - otherBase) < EPSILON;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }
}