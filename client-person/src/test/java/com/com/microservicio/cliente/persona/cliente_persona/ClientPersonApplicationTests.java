package com.microservicio.cliente.persona.cliente_persona;
import com.microservicio.cliente.persona.cliente_persona.entities.Client;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ClientPersonApplicationTests {

    private Validator validator;

    private Client client;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        client = new Client();
    }


    @Test
    public void testValidClient() {
        client.setClientId(1L);
        client.setCardId("123456789");
        client.setName("Jose Lema");
        client.setGender("Male");
        client.setAge(30);
        client.setAddress("Main St");
        client.setPhone("098254785");
        client.setPassword("1234");
        client.setEmail("jose.lema@example.com");
        client.setState("true");
        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testBlankState() {
        client.setState("");
        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        assertEquals(7, violations.size());
        assertEquals("The state must not be blank", violations.iterator().next().getMessage());
    }
}
