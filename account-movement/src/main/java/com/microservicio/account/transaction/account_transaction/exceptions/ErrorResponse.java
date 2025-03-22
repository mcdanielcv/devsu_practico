package com.microservicio.account.transaction.account_transaction.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private boolean status;
    private String message;
    private int codError;

    public ErrorResponse(boolean status, String message, int codError) {
        this.message = message;
        this.status = status;
        this.codError = codError;
    }

}
