package pt.calculator.tax.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseDataDto {
    private String netValue;
    private String grossValue;
    private String vatValue;
    private String vatRate;
}
