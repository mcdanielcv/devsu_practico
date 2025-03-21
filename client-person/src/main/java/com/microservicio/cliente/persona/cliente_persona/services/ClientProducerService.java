package com.microservicio.cliente.persona.cliente_persona.services;

import com.microservicio.cliente.persona.cliente_persona.models.ClientDTO;

public interface ClientProducerService {

    void sendClientMessage(Long clientId);
}
