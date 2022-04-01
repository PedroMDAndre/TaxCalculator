package pt.calculator.tax.services;

import pt.calculator.tax.exceptions.DataFieldException;
import pt.calculator.tax.model.PurchaseDataDto;

import java.util.List;

public interface PurchaseDataCalculatorService {
    PurchaseDataDto calculatePurchaseData(PurchaseDataDto purchaseDataDto) throws DataFieldException;
    List<Double> getVatRates();
}
