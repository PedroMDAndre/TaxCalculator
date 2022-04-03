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

    public PurchaseDataDto copy() {
        PurchaseDataDto copyDto =  new PurchaseDataDto();
        copyDto.setNetValue(this.netValue);
        copyDto.setGrossValue(this.grossValue);
        copyDto.setVatValue(this.vatValue);
        copyDto.setVatRate(this.vatRate);

        return copyDto;
    }
}
