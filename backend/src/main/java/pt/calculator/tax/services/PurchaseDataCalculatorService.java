package pt.calculator.tax.services;

import pt.calculator.tax.model.PurchaseDataDto;

public interface PurchaseDataCalculatorService {
    /**
     *
     * */
    PurchaseDataDto calculateTaxFields(PurchaseDataDto purchaseDataDto);
}
