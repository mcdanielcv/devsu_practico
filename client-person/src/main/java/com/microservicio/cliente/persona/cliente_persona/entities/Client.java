package com.microservicio.cliente.persona.cliente_persona.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Setter
@Getter
public class Client extends Person implements Serializable {

    @NotBlank(message = "The password must not be blank")
    private String password;
    @Column(unique = true)
    private String email;
    private boolean state;
}
