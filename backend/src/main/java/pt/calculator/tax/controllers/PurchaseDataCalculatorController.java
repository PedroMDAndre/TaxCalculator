package pt.calculator.tax.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;
import pt.calculator.tax.model.ErrorMessage;
import pt.calculator.tax.model.PurchaseDataDto;

import java.util.List;

public interface PurchaseDataCalculatorController {
    @Hidden
    @GetMapping(value = "/")
    RedirectView mainPage();

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
    ResponseEntity<PurchaseDataDto> createUser(@RequestBody PurchaseDataDto purchaseDataDto);

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
    ResponseEntity<List<Double>> getRates();
}
