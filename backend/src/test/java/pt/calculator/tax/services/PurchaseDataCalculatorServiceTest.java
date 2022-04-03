package pt.calculator.tax.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.calculator.tax.exceptions.DataFieldException;
import pt.calculator.tax.model.PurchaseDataDto;

@SpringBootTest
class PurchaseDataCalculatorServiceTest {
    @Autowired
    private PurchaseDataCalculatorService purchaseDataCalculatorService;
    private final String netValue = "1000.00";
    private final String grossValue = "1200.00";
    private final String vatValue = "200.00";
    private final String vatRate = "0.2";

    @Test
    void testCalculatePurchaseData() throws DataFieldException {
        // Arrange
        PurchaseDataDto purchaseDataDto = new PurchaseDataDto();
        purchaseDataDto.setGrossValue(grossValue);
        purchaseDataDto.setVatRate(vatRate);

        // Act
        PurchaseDataDto result = purchaseDataCalculatorService.calculatePurchaseData(purchaseDataDto);

        // Assert
        Assertions.assertEquals(netValue, result.getNetValue());
        Assertions.assertEquals(grossValue,result.getGrossValue());
        Assertions.assertEquals(vatValue, result.getVatValue());
        Assertions.assertEquals(vatRate, result.getVatRate());
    }

    @Test
    void testCalculatePurchaseDataThrowError() {
        // Arrange
        PurchaseDataDto purchaseDataDto = new PurchaseDataDto();
        purchaseDataDto.setNetValue(netValue);
        purchaseDataDto.setGrossValue(grossValue);
        purchaseDataDto.setVatRate(vatRate);

        // Act
        DataFieldException exception = Assertions.assertThrows(DataFieldException.class,
                () -> purchaseDataCalculatorService.calculatePurchaseData(purchaseDataDto));

        // Assert
        Assertions.assertEquals("More than one input field.", exception.getMessage());
    }
}
