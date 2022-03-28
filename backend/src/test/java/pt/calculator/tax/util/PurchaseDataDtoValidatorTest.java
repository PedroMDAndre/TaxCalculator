package pt.calculator.tax.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pt.calculator.tax.model.PurchaseDataDto;

@SpringBootTest
class PurchaseDataDtoValidatorTest {

    @Test
    void testPurchaseDataDtoValidatorValidInputField(){
        // Arrange
        PurchaseDataDto purchaseDataDto = new PurchaseDataDto();
        purchaseDataDto.setVatRate("0.2");
        purchaseDataDto.setGrossValue("100.00");
        PurchaseDataDtoValidator validator = new PurchaseDataDtoValidator(purchaseDataDto);

        // Act
        boolean isValid = validator.validate();

        // Validate
        Assertions.assertTrue(isValid);
    }

    @Test
    void testPurchaseDataDtoValidatorAllFieldsAreNull(){
        // Arrange
        PurchaseDataDto purchaseDataDto = new PurchaseDataDto();
        PurchaseDataDtoValidator validator = new PurchaseDataDtoValidator(purchaseDataDto);

        // Act
        boolean isValid =validator.validate();

        // Validate
        Assertions.assertFalse(isValid);
        Assertions.assertEquals(2, validator.getListErrors().size());
        Assertions.assertEquals("VAT rate input is missing.", validator.getListErrors().get(0));
        Assertions.assertEquals("Missing amount input.", validator.getListErrors().get(1));
    }

    @Test
    void testPurchaseDataDtoValidatorMoreThanOneField(){
        // Arrange
        PurchaseDataDto purchaseDataDto = new PurchaseDataDto();
        purchaseDataDto.setVatRate("0.2");
        purchaseDataDto.setNetValue("300.00");
        purchaseDataDto.setGrossValue("400.00");
        PurchaseDataDtoValidator validator = new PurchaseDataDtoValidator(purchaseDataDto);

        // Act
        boolean isValid =validator.validate();

        // Validate
        Assertions.assertFalse(isValid);
        Assertions.assertEquals(1, validator.getListErrors().size());
        Assertions.assertEquals("More than one input field.", validator.getListErrors().get(0));
    }

    @Test
    void testPurchaseDataDtoValidatorFieldIsNonNumeric(){
        // Arrange
        PurchaseDataDto purchaseDataDto = new PurchaseDataDto();
        purchaseDataDto.setVatRate("0.2");
        purchaseDataDto.setNetValue("hundred");
        PurchaseDataDtoValidator validator = new PurchaseDataDtoValidator(purchaseDataDto);

        // Act
        boolean isValid =validator.validate();

        // Validate
        Assertions.assertFalse(isValid);
        Assertions.assertEquals(1, validator.getListErrors().size());
        Assertions.assertEquals("Net input is non-numeric.", validator.getListErrors().get(0));
    }

    @Test
    void testPurchaseDataDtoValidatorFieldIsZero(){
        // Arrange
        PurchaseDataDto purchaseDataDto = new PurchaseDataDto();
        purchaseDataDto.setVatRate("0.2");
        purchaseDataDto.setGrossValue("0.00");
        PurchaseDataDtoValidator validator = new PurchaseDataDtoValidator(purchaseDataDto);

        // Act
        boolean isValid =validator.validate();

        // Validate
        Assertions.assertFalse(isValid);
        Assertions.assertEquals(1, validator.getListErrors().size());
        Assertions.assertEquals("Gross input is 0.", validator.getListErrors().get(0));
    }
}
