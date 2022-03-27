package pt.calculator.tax.services;

import org.springframework.stereotype.Service;
import pt.calculator.tax.model.PurchaseDataDto;

@Service
public class PurchaseDataCalculatorServiceImpl implements PurchaseDataCalculatorService {

    @Override
    public PurchaseDataDto calculateTaxFields(PurchaseDataDto purchaseDataDto) {
        double netValue = 0.0;
        double grossValue = 0.0;
        double vatValue = 0.0;
        double vatRate = 0.0;

        if (purchaseDataDto.getGrossValue() == null){
            // throw exception
        }

        vatRate = Double.parseDouble(purchaseDataDto.getVatRate());


        return purchaseDataDto;
    }

    private double calculateNetValueFromGrossValue(double grossValue, double vatRate) {
        return grossValue * (1 - vatRate);
    }

    private double calculateNetValueFromVatValue(double vatValue, double vatRate) {
        return vatValue / (vatRate);
    }

    private double calculateGrossValueFromNetValue(double netValue, double vatRate) {
        return netValue * (1 + vatRate);
    }

    private double calculateGrossValueFromVatValue(double vatValue, double vatRate) {
        return vatValue * (1 + vatRate) / vatRate;
    }

    private double calculateVatValueFromNetValue(double netValue, double vatRate) {
        return netValue * vatRate;
    }

    private double calculateVatValueFromGrossValue(double grossValue, double vatRate) {
        return grossValue / vatRate;
    }
}
