package com.microservicio.account.transaction.account_transaction.exceptions;

public class NoAccountsFoundException extends RuntimeException {
    public NoAccountsFoundException(String message) {
        super(message);
    }
}
