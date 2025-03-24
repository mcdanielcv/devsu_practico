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
    @NotBlank(message = "The email must not be blank")
    private String email;
    @NotBlank(message = "The state must not be blank")
    private String state;
}
