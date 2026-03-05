package com.bridgelabz;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // ===============================
        // Length Operations
        // ===============================

        Quantity<LengthUnit> length1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(6.0, LengthUnit.INCH);

        System.out.println("Length Equality: " +
                length1.equals(new Quantity<>(120.0, LengthUnit.INCH)));

        System.out.println("Length Conversion: " +
                length1.convertTo(LengthUnit.INCH));

        System.out.println("Length Addition: " +
                length1.add(length2));

        System.out.println("Length Subtraction: " +
                length1.subtract(length2));

        System.out.println("Length Division: " +
                length1.divide(new Quantity<>(2.0, LengthUnit.FEET)));



        // ===============================
        // Weight Operations
        // ===============================

        Quantity<WeightUnit> weight1 =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(5000.0, WeightUnit.GRAM);

        System.out.println("Weight Equality: " +
                weight1.equals(new Quantity<>(10000.0, WeightUnit.GRAM)));

        System.out.println("Weight Conversion: " +
                weight1.convertTo(WeightUnit.GRAM));

        System.out.println("Weight Addition: " +
                weight1.add(weight2));

        System.out.println("Weight Subtraction: " +
                weight1.subtract(weight2));

        System.out.println("Weight Division: " +
                weight1.divide(new Quantity<>(5.0, WeightUnit.KILOGRAM)));



        // ===============================
        // Volume Operations
        // ===============================

        Quantity<VolumeUnit> volume1 =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> volume2 =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        System.out.println("Volume Equality: " +
                volume1.equals(new Quantity<>(5000.0, VolumeUnit.MILLILITRE)));

        System.out.println("Volume Conversion: " +
                volume1.convertTo(VolumeUnit.MILLILITRE));

        System.out.println("Volume Addition: " +
                volume1.add(volume2));

        System.out.println("Volume Subtraction: " +
                volume1.subtract(volume2));

        System.out.println("Volume Division: " +
                volume1.divide(new Quantity<>(10.0, VolumeUnit.LITRE)));
    }
}