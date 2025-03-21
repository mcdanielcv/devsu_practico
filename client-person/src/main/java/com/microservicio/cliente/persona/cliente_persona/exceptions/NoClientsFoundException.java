package com.microservicio.cliente.persona.cliente_persona.exceptions;

public class NoClientsFoundException extends RuntimeException {
    public NoClientsFoundException(String message) {
        super(message);
    }
}
