package com.microservicio.account.transaction.account_transaction.exceptions;

public class MovementNotFoundException extends RuntimeException{
    public MovementNotFoundException(String message) {
        super(message);
    }
}
