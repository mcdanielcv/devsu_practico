package com.microservicio.cliente.persona.cliente_persona.models;

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
