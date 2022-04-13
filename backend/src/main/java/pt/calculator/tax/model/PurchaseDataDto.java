package pt.calculator.tax.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class PurchaseDataDto {
    private String netValue;
    private String grossValue;
    private String vatValue;
    private String vatRate;

    public PurchaseDataDto copy() {
        PurchaseDataDto copyDto =  new PurchaseDataDto();
        BeanUtils.copyProperties(this, copyDto);

        return copyDto;
    }
}
