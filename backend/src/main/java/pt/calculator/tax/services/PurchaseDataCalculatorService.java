package pt.calculator.tax.services;

import pt.calculator.tax.exceptions.DataFieldException;
import pt.calculator.tax.model.PurchaseDataDto;

public interface PurchaseDataCalculatorService {
    PurchaseDataDto calculatePurchaseData(PurchaseDataDto purchaseDataDto) throws DataFieldException;
}
