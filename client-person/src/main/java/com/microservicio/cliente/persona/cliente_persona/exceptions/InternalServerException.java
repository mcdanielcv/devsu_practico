package com.microservicio.cliente.persona.cliente_persona.exceptions;

public class InternalServerException extends RuntimeException {
    public InternalServerException(String message) {
        super(message);
    }
}
