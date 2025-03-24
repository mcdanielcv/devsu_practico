package com.microservicio.cliente.persona.cliente_persona;
import com.intuit.karate.junit5.Karate;

class ClientTests {

    @Karate.Test
    Karate testAll() {
        return Karate.run("com/microservicio/cliente/persona/cliente_persona/ClientTests.feature").relativeTo(getClass());
    }
}
