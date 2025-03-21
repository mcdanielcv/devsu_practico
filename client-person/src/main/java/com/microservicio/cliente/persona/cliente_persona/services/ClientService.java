package com.microservicio.cliente.persona.cliente_persona.services;

import com.microservicio.cliente.persona.cliente_persona.entities.Client;
import com.microservicio.cliente.persona.cliente_persona.models.ClientDTO;
import com.microservicio.cliente.persona.cliente_persona.models.ClientInputDTO;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getAllClients();

    ClientDTO saveClient(ClientInputDTO client);

    ClientDTO updateClient(ClientInputDTO client, Long id);

    ClientDTO deleteClientById(Long id);

    ClientDTO getClientById(@NonNull Long id);

    String getNameClientById(Long clientId);

    List<Long> getAllIdClients();
}
