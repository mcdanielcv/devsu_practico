package com.microservicio.account.transaction.account_transaction.exceptions;

public class NoTransactionsFoundException extends RuntimeException{
    public NoTransactionsFoundException(String message) {
        super(message);
    }
}
