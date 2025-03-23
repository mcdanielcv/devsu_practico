package com.microservicio.cliente.persona.cliente_persona.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class ClientInputDTO implements Serializable {
    private Long clientId;
    @NotBlank(message = "The ID must not be blank")
    private String cardId;
    @NotBlank(message = "The name must not be blank")
    private String name;
    @NotBlank(message = "The  mail must not be blank")
    @Email(message = "The  mail is not in given pattern")
    private String email;
    private String gender;
    @Min(value = 1, message = "Number should meet min ")
    @Max(value = 100, message = "Number should meet max")
    private int age;
    @NotBlank(message = "The address must not be blank")
    private String address;
    @NotBlank(message = "The phone must not be blank")
    private String phone;
    @NotBlank(message = "The password must not be blank")
    private String password;
    @NotBlank(message = "The state must not be blank")
    private String state;
}
