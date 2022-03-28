package pt.calculator.tax.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.calculator.tax.model.PurchaseDataDto;
import pt.calculator.tax.services.PurchaseDataCalculatorService;

@RestController
public class PurchaseDataCalculatorController {

    @Autowired
    private PurchaseDataCalculatorService purchaseDataCalculatorService;

    @PostMapping(value = "/calculate",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody PurchaseDataDto purchaseDataDto) {
        try {
            purchaseDataDto = purchaseDataCalculatorService.calculatePurchaseData(purchaseDataDto);
            return ResponseEntity.status(HttpStatus.OK).body(purchaseDataDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
