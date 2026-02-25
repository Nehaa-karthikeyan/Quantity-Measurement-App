package com.bridgelabz;

public class QuantityMeasurementApp {

    // Inner class representing Feet measurement
    public static class Feet {

        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            // Step 1: Reference check
            if (this == obj)
                return true;

            // Step 2: Null or type check
            if (obj == null || getClass() != obj.getClass())
                return false;

            // Step 3: Cast safely
            Feet other = (Feet) obj;

            // Step 4: Compare using Double.compare
            return Double.compare(this.value, other.value) == 0;
        }
    }
}