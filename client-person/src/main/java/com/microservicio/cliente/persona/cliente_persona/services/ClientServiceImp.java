package com.microservicio.cliente.persona.cliente_persona.services;

import com.microservicio.cliente.persona.cliente_persona.entities.Client;
import com.microservicio.cliente.persona.cliente_persona.exceptions.ClientNotFoundException;
import com.microservicio.cliente.persona.cliente_persona.exceptions.DuplicateEntryException;
import com.microservicio.cliente.persona.cliente_persona.exceptions.InternalServerException;
import com.microservicio.cliente.persona.cliente_persona.exceptions.NoClientsFoundException;
import com.microservicio.cliente.persona.cliente_persona.models.ClientDTO;
import com.microservicio.cliente.persona.cliente_persona.models.ClientInputDTO;
import com.microservicio.cliente.persona.cliente_persona.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImp implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired(required = true)
    private EncryptService encryptService;

    @Autowired
    private ClientProducerService clientProducerService;


    @Transactional(readOnly = true)
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        if (clients.isEmpty()) {
            throw new NoClientsFoundException("No clients were found in the database.");
        }
        return clients.stream().map(client -> ClientMapper.INSTANCE.ClientToDto(client)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClientDTO getClientById(@NonNull Long id) {
        return ClientMapper.INSTANCE.ClientToDto(getClientDbById(id));
    }

    @Transactional
    public ClientDTO saveClient(ClientInputDTO client) {
        try {
            Client clientDb = new Client();
            clientDb.setCardId(client.getCardId());
            clientDb.setName(client.getName());
            clientDb.setEmail(client.getEmail());
            clientDb.setAddress(client.getAddress());
            clientDb.setPhone(client.getPhone());
            clientDb.setGender(client.getGender());
            clientDb.setAge(client.getAge());
            clientDb.setState(client.isState());
            clientDb.setPassword(encryptService.encryptPassword(client.getPassword()));
            ClientDTO clientDTO = ClientMapper.INSTANCE.ClientToDto(clientRepository.save(clientDb));
            //clientProducerService.sendClientMessage(clientDTO.getClientId());
            return clientDTO;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEntryException("The client already exists with card Id: " + client.getCardId());
        } catch (RuntimeException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Transactional
    public ClientDTO updateClient(ClientInputDTO client, Long id) {
        try {
            Client clientDb = getClientDbById(id);
            clientDb.setName(client.getName());
            clientDb.setEmail(client.getEmail());
            clientDb.setAddress(client.getAddress());
            clientDb.setPhone(client.getPhone());
            clientDb.setGender(client.getGender());
            clientDb.setAge(client.getAge());
            clientDb.setState(client.isState());
            clientDb.setPassword(encryptService.encryptPassword(client.getPassword()));
            return ClientMapper.INSTANCE.ClientToDto(clientRepository.save(clientDb));
        } catch (ClientNotFoundException ex) {
            throw new ClientNotFoundException(ex.getMessage());
        } catch (RuntimeException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Transactional
    public ClientDTO deleteClientById(Long id) {
        try {
            Client clientDb = getClientDbById(id);
            clientRepository.deleteById(id);
            return ClientMapper.INSTANCE.ClientToDto(clientDb);
        } catch (ClientNotFoundException ex) {
            throw new ClientNotFoundException(ex.getMessage());
        } catch (RuntimeException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public String getNameClientById(Long clienteId) {
        try {
            return getClientDbById(clienteId).getName();
        } catch (ClientNotFoundException ex) {
            throw new ClientNotFoundException(ex.getMessage());
        } catch (RuntimeException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    private Client getClientDbById(Long id) {
        Optional<Client> clienteOptional = clientRepository.findById(id);
        return clienteOptional.orElseThrow(() ->
                new ClientNotFoundException("Cliente no encontrado con ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<Long> getAllIdClients() {
        List<Integer> idInteger = clientRepository.findAllIdClients();
        List<Long> idsAsLong = idInteger.stream()
                .map(Number::longValue)  // Convierte a Long
                .collect(Collectors.toList());
        return idsAsLong;
    }
}
