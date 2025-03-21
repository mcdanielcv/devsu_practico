package com.microservicio.cliente.persona.cliente_persona;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicio.cliente.persona.cliente_persona.entities.Client;
import com.microservicio.cliente.persona.cliente_persona.repositories.ClientRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ClientPersonApplicationTests {


	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

 	private Client client;

    @BeforeEach
    public void setUp() {
        client = new Client();
        client.setClientId(4L);
        client.setName("Jose Lema");
        client.setAge(30);
        client.setPassword("9875");
        client.setState(true);
    }


	@Test
	void contextLoads() {
		assertNotNull(client.getClientId());
        assertEquals(4L, client.getClientId());
		assertEquals("9875", client.getPassword());
	}


	@Test
	void TestCrearCliente() throws Exception{
		        Client cliente = new Client();
				cliente.setName("Jose Lema");
				cliente.setAge(30);
				cliente.setPassword("9875");
				cliente.setState(true);

        ObjectMapper objectMapper = new ObjectMapper();
        String clienteJson = objectMapper.writeValueAsString(cliente);

		mockMvc.perform(post("/clients")
		.contentType(MediaType.APPLICATION_JSON)
		.content(clienteJson))
		.andExpect(status().isOk());

		// valida que el cliente esta en la base
		Optional<Client> clienteGuardado = clientRepository.findById(1L);
		if (clienteGuardado.isPresent()) {
			assertThat(clienteGuardado.get().getGender()).isNotNull();
			assertThat(clienteGuardado.get().getName()).isEqualTo("Jose Lema");
		}
	}
	

}
