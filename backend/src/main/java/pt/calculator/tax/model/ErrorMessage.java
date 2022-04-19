package pt.calculator.tax.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
    private String message;
    private long timestamp;
    private int status;
    private String error;
    private String path;

}
