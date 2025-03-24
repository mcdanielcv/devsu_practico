package com.microservicio.cliente.persona.cliente_persona.services;

import com.microservicio.cliente.persona.cliente_persona.models.ClientDTO;
import com.microservicio.cliente.persona.cliente_persona.models.ClientInputDTO;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Map;

public interface ClientService {

    List<ClientDTO> getAllClients();

    ClientDTO saveClient(ClientInputDTO client);

    ClientDTO updateClient(Map<String, Object> actualizaciones, Long id);

    ClientDTO deleteClientById(Long id);

    ClientDTO getClientById(@NonNull Long id);

    String getNameClientById(Long clientId);

    List<Long> getAllIdClients();

    ClientDTO saveClientWithAccount(ClientInputDTO client);
}
