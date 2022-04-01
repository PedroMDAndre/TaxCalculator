package pt.calculator.tax.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.calculator.tax.exceptions.DataFieldException;
import pt.calculator.tax.model.PurchaseDataDto;
import pt.calculator.tax.services.PurchaseDataCalculatorService;

import java.util.List;

@RestController
public class PurchaseDataCalculatorController {
    private final static String CANNOT_GET_TAX_RATES = "It was not possible to the tax rates.";

    @Autowired
    private PurchaseDataCalculatorService purchaseDataCalculatorService;

    @PostMapping(value = "/calculate",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody PurchaseDataDto purchaseDataDto) {
        try {
            purchaseDataDto = purchaseDataCalculatorService.calculatePurchaseData(purchaseDataDto);
            return ResponseEntity.status(HttpStatus.OK).body(purchaseDataDto);
        } catch (DataFieldException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "/rates",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getRates() {
        try {
            List<Double> vatRates = purchaseDataCalculatorService.getVatRates();
            return ResponseEntity.status(HttpStatus.OK).body(vatRates);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CANNOT_GET_TAX_RATES);
        }
    }
}
