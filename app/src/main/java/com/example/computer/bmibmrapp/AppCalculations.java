package com.example.computer.bmibmrapp;

import java.text.DecimalFormat;

public class AppCalculations {
    public double bmiCalculation(double height, double weight) {
        DecimalFormat decimalFormat = new DecimalFormat(".##");
        double resultDouble = weight / (height * height) * 10000;
        String result = decimalFormat.format(resultDouble);
        return Double.parseDouble(result);
    }

    public double bmrFemaleCalculation(double height, double weight, double age) {
        DecimalFormat decimalFormat = new DecimalFormat(".##");
        double resultDouble = (height * 6.25) + (weight * 9.99) - (age * 4.92) - 161;
        String result = decimalFormat.format(resultDouble);
        return Double.parseDouble(result);
    }

    public double bmrMaleCalculation(double height, double weight, double age) {
        DecimalFormat decimalFormat = new DecimalFormat(".##");
        double resultDouble = (height * 6.25) + (weight * 9.99) - (age * 4.92) + 5;
        String result = decimalFormat.format(resultDouble);
        return Double.parseDouble(result);
    }
}