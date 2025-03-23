package com.microservicio.cliente.persona.cliente_persona.exceptions;

public class DuplicateCardIdException extends RuntimeException{
    public DuplicateCardIdException(String message) {
        super(message);
    }
}
