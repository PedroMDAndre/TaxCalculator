package pt.calculator.tax.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PurchaseDataCalculatorAustriaTest {
    private final PurchaseDataCalculator calculator = new PurchaseDataCalculatorAustria();
    private final double netValue = 1000.00;
    private final double grossValue = 1200.00;
    private final double vatValue = 200.00;
    private final double vatRate = 0.2;

    @Test
    void calculateNetValueFromGrossValue() {
        // Arrange - Act
        double result = calculator.calculateNetValueFromGrossValue(grossValue, vatRate);

        // Assert
        Assertions.assertEquals(netValue, result);
    }

    @Test
    void calculateNetValueFromVatValue() {
        // Arrange - Act
        double result = calculator.calculateNetValueFromVatValue(vatValue, vatRate);

        // Assert
        Assertions.assertEquals(netValue, result);
    }

    @Test
    void calculateGrossValueFromNetValue() {
        // Arrange - Act
        double result = calculator.calculateGrossValueFromNetValue(netValue, vatRate);

        // Assert
        Assertions.assertEquals(grossValue, result);
    }

    @Test
    void calculateGrossValueFromVatValue() {
        // Arrange - Act
        double result = calculator.calculateGrossValueFromVatValue(vatValue, vatRate);

        // Assert
        Assertions.assertEquals(grossValue, result);
    }

    @Test
    void calculateVatValueFromNetValue() {
        // Arrange - Act
        double result = calculator.calculateVatValueFromNetValue(netValue, vatRate);

        // Assert
        Assertions.assertEquals(vatValue, result);
    }

    @Test
    void calculateVatValueFromGrossValue() {
        // Arrange - Act
        double result = calculator.calculateVatValueFromGrossValue(grossValue, vatRate);

        // Assert
        Assertions.assertEquals(vatValue, result);
    }
}
