package com.microservicio.cliente.persona.cliente_persona.services;

import com.microservicio.cliente.persona.cliente_persona.entities.Client;
import com.microservicio.cliente.persona.cliente_persona.models.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(source = "clientId", target = "clientId")
    Client dtoToClient(ClientDTO clientDTO);

    @Mapping(source = "clientId", target = "clientId")
    ClientDTO ClientToDto(Client client);

}
