package com.microservicio.cliente.persona.cliente_persona.exceptions;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String message) {
        super(message);
    }
}
