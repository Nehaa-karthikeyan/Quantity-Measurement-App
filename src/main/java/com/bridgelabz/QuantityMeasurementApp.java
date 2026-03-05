package com.bridgelabz;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCH);

        System.out.println("Addition:");
        System.out.println(q1.add(q2));

        System.out.println("Subtraction:");
        System.out.println(q1.subtract(q2));

        System.out.println("Division:");
        System.out.println(q1.divide(q2));

        System.out.println("Conversion:");
        System.out.println(q1.convertTo(LengthUnit.INCH));
    }
}