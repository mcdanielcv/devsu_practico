package com.microservicio.cliente.persona.cliente_persona;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicio.cliente.persona.cliente_persona.entities.Client;
import com.microservicio.cliente.persona.cliente_persona.repositories.ClientRepository;

@SpringBootTest
@AutoConfigureMockMvc

public class IntegrationTest {
@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testCrearCliente() throws Exception {
        /*
        Client cliente = new Client();
        cliente.setNombre("Jose Lema");
		cliente.setEdad(30);
		cliente.setContrasena("9875");
        cliente.setTelefono("0939241848");
        cliente.setDireccion("Calderon");
        cliente.setIdentificacion("1721521613");
		cliente.setEstado(true);


        ObjectMapper objectMapper = new ObjectMapper();
        String clienteJson = objectMapper.writeValueAsString(cliente);

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clienteJson))
                .andDo(print())
                .andExpect(status().isCreated());

        // Verifica que el cliente fue guardado en la base de datos
        List<Client> list = clientRepository.findByIdentificacion("1721521613");
        Client clienteDb = list.get(0);

        assertThat(clienteDb).isNotNull();
        assertThat(clienteDb.getNombre()).isEqualTo("Jose Lema");
    }*/
    }
}
