package pt.calculator.tax.services;

import org.springframework.stereotype.Service;
import pt.calculator.tax.exceptions.DataFieldException;
import pt.calculator.tax.model.PurchaseDataDto;
import pt.calculator.tax.util.PurchaseDataCalculator;
import pt.calculator.tax.util.PurchaseDataCalculatorAustria;
import pt.calculator.tax.util.PurchaseDataDtoValidator;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class PurchaseDataCalculatorServiceImpl implements PurchaseDataCalculatorService {

    private static final String UNKNOWN_FIELD = "Unknown field.";

    @Override
    public PurchaseDataDto calculatePurchaseData(PurchaseDataDto purchaseDataDto) throws DataFieldException {
        PurchaseDataDtoValidator validator = new PurchaseDataDtoValidator(purchaseDataDto);

        if (!validator.validate()) {
            throw new DataFieldException(errorMessage(validator.getListErrors()));
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

                purchaseDataDto.setGrossValue(valueToString(grossValueResult));
                purchaseDataDto.setVatValue(valueToString(vatValueResult));
            }

            case GROSS -> {
                double netValueResult = calculator.calculateNetValueFromGrossValue(grossValue, vatRateValue);
                double vatValueResult = calculator.calculateVatValueFromGrossValue(grossValue, vatRateValue);

                purchaseDataDto.setNetValue(valueToString(netValueResult));
                purchaseDataDto.setVatValue(valueToString(vatValueResult));
            }

            case VAT -> {
                double netValueResult = calculator.calculateNetValueFromVatValue(vatValue, vatRateValue);
                double grossValueResult = calculator.calculateGrossValueFromVatValue(vatValue, vatRateValue);

                purchaseDataDto.setNetValue(valueToString(netValueResult));
                purchaseDataDto.setGrossValue(valueToString(grossValueResult));
            }

            default -> throw new DataFieldException(UNKNOWN_FIELD);
        }

        return purchaseDataDto;
    }

    @Override
    public List<Double> getVatRates() {
        Double[] vatRates = {0.1, 0.13, 0.2};
        return List.of(vatRates);
    }

    private String valueToString(double value) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value).replace(",", ".");
    }

    private String errorMessage(List<String> listOfErrors) {
        StringBuilder sb = new StringBuilder();
        for (String error : listOfErrors) {
            sb.append(error).append(" ");
        }
        return sb.toString().trim();
    }
}
