package pt.calculator.tax.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import pt.calculator.tax.exceptions.DataFieldException;
import pt.calculator.tax.model.ErrorMessage;
import pt.calculator.tax.model.PurchaseDataDto;
import pt.calculator.tax.services.PurchaseDataCalculatorService;

import java.util.List;

@RestController
public class PurchaseDataCalculatorControllerImpl implements PurchaseDataCalculatorController {
    private static final String CANNOT_GET_TAX_RATES = "It was not possible to get the tax rates.";
    private static final String SWAGGER_UI_URL = "/swagger-ui/index.html";

    private final PurchaseDataCalculatorService purchaseDataCalculatorService;

    public PurchaseDataCalculatorControllerImpl(PurchaseDataCalculatorService purchaseDataCalculatorService) {
        this.purchaseDataCalculatorService = purchaseDataCalculatorService;
    }

    @Override
    @GetMapping(value = "/")
    public RedirectView mainPage() {
        return new RedirectView(SWAGGER_UI_URL);
    }

    @Override
    @PostMapping(value = "/calculate",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody PurchaseDataDto purchaseDataDto) {
        try {
            purchaseDataDto = purchaseDataCalculatorService.calculatePurchaseData(purchaseDataDto);
            return ResponseEntity.status(HttpStatus.OK).body(purchaseDataDto);
        } catch (DataFieldException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    @Override
    @GetMapping(value = "/rates",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getRates() {
        try {
            List<Double> vatRates = purchaseDataCalculatorService.getVatRates();
            return ResponseEntity.status(HttpStatus.OK).body(vatRates);
        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage(CANNOT_GET_TAX_RATES);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
}
