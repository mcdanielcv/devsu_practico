package com.microservicio.cliente.persona.cliente_persona.services;

import com.microservicio.cliente.persona.cliente_persona.configuration.RabbitMQConfig;
import com.microservicio.cliente.persona.cliente_persona.models.ClientDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientProducerServiceImp implements ClientProducerService{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendClientMessage(Long clientId) {
        log.info("clienteDto->"+clientId);
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_CLIENT, clientId);
        log.info("salio clienteDto->"+clientId);
    }
}
