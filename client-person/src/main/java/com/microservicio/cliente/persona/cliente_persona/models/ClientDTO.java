package com.microservicio.cliente.persona.cliente_persona.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClientDTO implements Serializable {
    private Long clientId;
    private String cardId;
    private String name;
    private String email;
    private String address;
    private String phone;
}
