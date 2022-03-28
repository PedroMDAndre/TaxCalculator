package pt.calculator.tax.util;

import lombok.Getter;
import pt.calculator.tax.model.PurchaseDataDto;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PurchaseDataDtoValidator {
    private static final String ERROR_FIELD_IS_ZERO = "%s input is 0.";
    private static final String ERROR_FIELD_IS_NON_NUMERIC = "%s input is non-numeric.";
    private static final String ERROR_VAT_RATE_IS_MISSING = "VAT rate input is missing.";
    private static final String ERROR_MULTIPLE_INPUTS = "More than one input field.";
    private static final String ERROR_MISSING_INPUT = "Missing amount input.";

    private final List<String> listErrors;
    private final PurchaseDataDto purchaseDataDto;
    private InputField inputField;
    private double netValue;
    private double grossValue;
    private double vatValue;
    private double vatRateValue;

    public PurchaseDataDtoValidator(PurchaseDataDto purchaseDataDto) {
        this.purchaseDataDto = purchaseDataDto;
        this.listErrors = new ArrayList<>();
    }

    /**
     * Validate the input fields and add error messages to the error list.
     */
    public boolean validate() {
        listErrors.clear();

        validateVatRate();
        validateOnlyOneInputField();

        return listErrors.isEmpty();
    }

    /**
     * Validate VAT Rate.<br>
     * Add message to the error list if missing or invalid (0 or non-numeric) VAT rate input
     */
    private void validateVatRate() {
        String vatRateStr = purchaseDataDto.getVatRate();
        if (vatRateStr != null) {
            validateInputField(vatRateStr, InputField.VAT_RATE);
        } else {
            listErrors.add(ERROR_VAT_RATE_IS_MISSING);
        }
    }

    /**
     * Validate if there is only one input field.<br>
     * Add messages to the error list, if there are more than one input field, an input field is missing or
     * the field is zero or non-numeric.
     */
    private void validateOnlyOneInputField() {
        int numberOfInputFields = 0;
        String netValueStr = purchaseDataDto.getNetValue();
        String grossValueStr = purchaseDataDto.getGrossValue();
        String vatValueStr = purchaseDataDto.getVatValue();

        if (netValueStr != null && !netValueStr.isEmpty()) {
            numberOfInputFields++;
            validateInputField(netValueStr, InputField.NET);
        }

        if (grossValueStr != null && !grossValueStr.isEmpty()) {
            numberOfInputFields++;
            validateInputField(grossValueStr, InputField.GROSS);
        }

        if (vatValueStr != null && !vatValueStr.isEmpty()) {
            numberOfInputFields++;
            validateInputField(vatValueStr, InputField.VAT);
        }

        if (numberOfInputFields > 1) {
            listErrors.add(ERROR_MULTIPLE_INPUTS);
        } else if (numberOfInputFields == 0) {
            listErrors.add(ERROR_MISSING_INPUT);
        }
    }

    /**
     * Validate if a given field value is valid.<br>
     * Add a message to the error list, if the field is zero or non-numeric.
     */
    private void validateInputField(String fieldValueStr, InputField inputFieldToValidate) {
        try {
            double inputFieldValue = Double.parseDouble(fieldValueStr);
            if (inputFieldValue != 0.0) {

                switch (inputFieldToValidate) {
                    case NET -> netValue = inputFieldValue;
                    case GROSS -> grossValue = inputFieldValue;
                    case VAT -> vatValue = inputFieldValue;
                    case VAT_RATE -> vatRateValue = inputFieldValue;
                }

                if (!InputField.VAT_RATE.equals(inputFieldToValidate)) {
                    inputField = inputFieldToValidate;
                }

            } else {
                listErrors.add(String.format(ERROR_FIELD_IS_ZERO, inputFieldToValidate.getName()));
            }
        } catch (NumberFormatException e) {
            listErrors.add(String.format(ERROR_FIELD_IS_NON_NUMERIC, inputFieldToValidate.getName()));
        }
    }
}
