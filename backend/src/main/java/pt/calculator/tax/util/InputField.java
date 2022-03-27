package pt.calculator.tax.util;

import lombok.Getter;

@Getter
public enum InputField {
    NET("Net"),
    GROSS("Gross"),
    VAT("VAT"),
    VAT_RATE("VAT rate");

    private final String name;

    InputField(String name){
     this.name = name;
    }


}
