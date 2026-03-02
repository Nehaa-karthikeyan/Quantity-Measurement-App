package com.bridgelabz;

import java.util.Objects;

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
    // Conversion
    // ===============================
    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(converted, targetUnit);
    }

    // ===============================
    // Addition (Implicit Target)
    // ===============================
    public Quantity<U> add(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        double baseSum =
                unit.convertToBaseUnit(value)
                        + other.unit.convertToBaseUnit(other.value);

        double result =
                unit.convertFromBaseUnit(baseSum);

        return new Quantity<>(result, unit);
    }

    // ===============================
    // Addition (Explicit Target)
    // ===============================
    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseSum =
                unit.convertToBaseUnit(value)
                        + other.unit.convertToBaseUnit(other.value);

        double result =
                targetUnit.convertFromBaseUnit(baseSum);

        return new Quantity<>(result, targetUnit);
    }

    // ===============================
    // Equality
    // ===============================
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double thisBase = unit.convertToBaseUnit(value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - otherBase) < EPSILON;
    }

    @Override
    public int hashCode() {
        double baseValue = unit.convertToBaseUnit(value);
        return Objects.hash(baseValue, unit.getClass());
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}