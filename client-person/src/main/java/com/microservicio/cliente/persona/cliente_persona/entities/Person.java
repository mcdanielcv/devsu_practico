package com.microservicio.cliente.persona.cliente_persona.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    @NotBlank(message = "The ID must not be blank")
    private String cardId;
    @NotBlank(message = "The name must not be blank")
    private String name;
    private String gender;
    private int age;
    @NotBlank(message = "The address must not be blank")
    private String address;
    @NotBlank(message = "The phone must not be blank")
    private String phone;
}
