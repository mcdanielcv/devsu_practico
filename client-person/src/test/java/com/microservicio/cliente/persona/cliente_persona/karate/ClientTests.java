package com.microservicio.cliente.persona.cliente_persona.karate;
import com.intuit.karate.junit5.Karate;

class ClientTests {

    @Karate.Test
    Karate testAll() {
        return Karate.run("/ClientTests.feature").relativeTo(getClass());
    }
}
