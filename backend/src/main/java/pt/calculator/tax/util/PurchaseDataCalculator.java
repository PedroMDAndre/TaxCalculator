package pt.calculator.tax.util;

public interface PurchaseDataCalculator {
    double calculateNetValueFromGrossValue(double grossValue, double vatRate);

    double calculateNetValueFromVatValue(double vatValue, double vatRate);

    double calculateGrossValueFromNetValue(double netValue, double vatRate);

    double calculateGrossValueFromVatValue(double vatValue, double vatRate);

    double calculateVatValueFromNetValue(double netValue, double vatRate);

    double calculateVatValueFromGrossValue(double grossValue, double vatRate);
}
