package com.microservicio.cliente.persona.cliente_persona.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_CLIENT = "clientQueue";
    public static final String QUEUE_ACCOUNT = "accountQueue";

    @Bean
    public Queue clientQueue() {
        return new Queue(QUEUE_CLIENT, false);
    }

    @Bean
    public Queue accountQueue() {
        return new Queue(QUEUE_ACCOUNT, false);
    }
}
