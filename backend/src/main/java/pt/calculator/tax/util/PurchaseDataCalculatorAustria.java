package pt.calculator.tax.util;

public class PurchaseDataCalculatorAustria implements PurchaseDataCalculator {

    @Override
    public double calculateNetValueFromGrossValue(double grossValue, double vatRate) {
        return grossValue / (1 + vatRate);
    }

    @Override
    public double calculateNetValueFromVatValue(double vatValue, double vatRate) {
        return vatValue / (vatRate);
    }

    @Override
    public double calculateGrossValueFromNetValue(double netValue, double vatRate) {
        return netValue * (1 + vatRate);
    }

    @Override
    public double calculateGrossValueFromVatValue(double vatValue, double vatRate) {
        return vatValue * (1 + vatRate) / vatRate;
    }

    @Override
    public double calculateVatValueFromNetValue(double netValue, double vatRate) {
        return netValue * vatRate;
    }

    @Override
    public double calculateVatValueFromGrossValue(double grossValue, double vatRate) {
        return grossValue * vatRate / (1 + vatRate);
    }
}
