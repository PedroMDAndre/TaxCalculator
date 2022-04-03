package pt.calculator.tax.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PurchaseDataCalculatorController {
    private static final String CANNOT_GET_TAX_RATES = "It was not possible to get the tax rates.";

    @Autowired
    private PurchaseDataCalculatorService purchaseDataCalculatorService;

    @Hidden
    @GetMapping(value = "/")
    public RedirectView mainPage() {
        return  new RedirectView("/swagger-ui/index.html");
    }

    @Operation(summary = "Calculate Purchase data. Only one input field should be given and the tax rate.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Purchase data was calculated.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PurchaseDataDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input data.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}
            )
    })
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

    @Operation(summary = "Tax rates.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tax rates",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "400", description = "It was not possible to get the tax rates.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}
            )
    })
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
