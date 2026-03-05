package com.bridgelabz;

@FunctionalInterface
interface SupportsArithmetic {
    boolean isSupported();
}

public interface IMeasurable {

    // Convert to base unit
    double convertToBaseUnit(double value);

    // Convert from base unit
    double convertFromBaseUnit(double value);

    // Default lambda – arithmetic supported
    SupportsArithmetic supportsArithmetic = () -> true;

    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    // Default validation (all units allowed unless overridden)
    default void validateOperationSupport(String operation) {
        // do nothing by default
    }
}