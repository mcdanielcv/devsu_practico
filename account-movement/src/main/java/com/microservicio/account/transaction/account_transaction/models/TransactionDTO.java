package com.microservicio.cuenta.movimiento.cuenta_movimiento.com.microservicio.account.transaction.account_transaction.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TransactionDTO {

    private Long numTransaccion;
    private Long accountNumber;
    private Date transactionDate;
    private String typeMovement;
    private double value;


}
