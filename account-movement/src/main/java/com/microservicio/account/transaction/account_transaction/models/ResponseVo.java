package com.microservicio.cuenta.movimiento.cuenta_movimiento.com.microservicio.account.transaction.account_transaction.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseVo implements Serializable{
    private boolean status;
    private String message;
    private Object data;

    public ResponseVo(boolean status, String message,Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseVo(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
