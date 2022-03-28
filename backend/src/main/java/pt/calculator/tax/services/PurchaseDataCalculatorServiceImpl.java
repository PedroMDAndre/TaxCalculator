package pt.calculator.tax.services;

import org.springframework.stereotype.Service;
import pt.calculator.tax.exceptions.DataFieldException;
import pt.calculator.tax.model.PurchaseDataDto;
import pt.calculator.tax.util.PurchaseDataCalculator;
import pt.calculator.tax.util.PurchaseDataCalculatorAustria;
import pt.calculator.tax.util.PurchaseDataDtoValidator;

@Service
public class PurchaseDataCalculatorServiceImpl implements PurchaseDataCalculatorService {

    @Override
    public PurchaseDataDto calculateTaxFields(PurchaseDataDto purchaseDataDto) throws DataFieldException {
        PurchaseDataDtoValidator validator = new PurchaseDataDtoValidator(purchaseDataDto);

        if (!validator.validate()) {
            throw new DataFieldException(validator.getListErrors().toString());
        }

        double netValue = validator.getNetValue();
        double grossValue = validator.getGrossValue();
        double vatValue = validator.getVatValue();
        double vatRateValue = validator.getVatRateValue();

        PurchaseDataCalculator calculator = new PurchaseDataCalculatorAustria();

        switch (validator.getInputField()) {
            case NET -> {
                double grossValueResult = calculator.calculateGrossValueFromNetValue(netValue, vatRateValue);
                double vatValueResult = calculator.calculateVatValueFromNetValue(netValue, vatRateValue);

                purchaseDataDto.setGrossValue(String.valueOf(grossValueResult));
                purchaseDataDto.setVatValue(String.valueOf(vatValueResult));
            }
            case GROSS -> {
                double netValueResult = calculator.calculateNetValueFromGrossValue(grossValue, vatRateValue);
                double vatValueResult = calculator.calculateVatValueFromGrossValue(grossValue, vatRateValue);

                purchaseDataDto.setNetValue(String.valueOf(netValueResult));
                purchaseDataDto.setVatValue(String.valueOf(vatValueResult));
            }
            case VAT -> {
                double netValueResult = calculator.calculateNetValueFromVatValue(vatValue, vatRateValue);
                double grossValueResult = calculator.calculateGrossValueFromVatValue(vatValue, vatRateValue);

                purchaseDataDto.setNetValue(String.valueOf(netValueResult));
                purchaseDataDto.setGrossValue(String.valueOf(grossValueResult));
            }
            default -> { // Throw an Exception
            }
        }

        return purchaseDataDto;
    }
}
