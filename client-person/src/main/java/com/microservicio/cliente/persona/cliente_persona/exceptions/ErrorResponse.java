package com.microservicio.cliente.persona.cliente_persona.exceptions;

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
