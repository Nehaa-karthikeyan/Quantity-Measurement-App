package com.bridgelabz;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    private static final double EPSILON = 1e-6;

    public Quantity(double value, U unit) {

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

    public U getUnit() {
        return unit;
    }

    // ===============================
    // ENUM FOR ARITHMETIC OPERATIONS
    // ===============================
    private enum ArithmeticOperation {

        ADD {
            public double compute(double a, double b) {
                return a + b;
            }
        },

        SUBTRACT {
            public double compute(double a, double b) {
                return a - b;
            }
        },

        DIVIDE {
            public double compute(double a, double b) {
                if (Math.abs(b) < EPSILON)
                    throw new ArithmeticException("Division by zero");
                return a / b;
            }
        };

        public abstract double compute(double a, double b);
    }

    // ===============================
    // CENTRALIZED VALIDATION
    // ===============================
    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetUnitRequired) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Incompatible unit categories");

        if (!Double.isFinite(other.value))
            throw new IllegalArgumentException("Value must be finite");

        if (targetUnitRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
    }

    // ===============================
    // CORE HELPER METHOD (DRY)
    // ===============================
    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation) {

        double baseThis = unit.convertToBaseUnit(value);
        double baseOther = other.unit.convertToBaseUnit(other.value);

        return operation.compute(baseThis, baseOther);
    }

    private double roundToTwoDecimals(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    // ===============================
    // ADD METHODS
    // ===============================
    public Quantity<U> add(Quantity<U> other) {

        validateArithmeticOperands(other, unit, false);

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result =
                unit.convertFromBaseUnit(baseResult);

        return new Quantity<>(roundToTwoDecimals(result), unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result =
                targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(roundToTwoDecimals(result), targetUnit);
    }

    // ===============================
    // SUBTRACT METHODS
    // ===============================
    public Quantity<U> subtract(Quantity<U> other) {

        validateArithmeticOperands(other, unit, false);

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result =
                unit.convertFromBaseUnit(baseResult);

        return new Quantity<>(roundToTwoDecimals(result), unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result =
                targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(roundToTwoDecimals(result), targetUnit);
    }

    // ===============================
    // DIVISION
    // ===============================
    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    // ===============================
    // CONVERSION
    // ===============================
    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = unit.convertToBaseUnit(value);

        double converted =
                targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(converted, targetUnit);
    }

    // ===============================
    // EQUALITY
    // ===============================
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Quantity<?> other))
            return false;

        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double baseThis = unit.convertToBaseUnit(value);
        double baseOther =
                ((IMeasurable) other.unit)
                        .convertToBaseUnit(other.value);

        return Math.abs(baseThis - baseOther) < EPSILON;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}