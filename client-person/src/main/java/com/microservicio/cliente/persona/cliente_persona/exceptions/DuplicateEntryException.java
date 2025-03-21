package com.microservicio.cliente.persona.cliente_persona.exceptions;

public class DuplicateEntryException extends RuntimeException {
    public DuplicateEntryException(String message) {
        super(message);
    }
}
