package com.microservicio.cliente.persona.cliente_persona.controllers;

import com.microservicio.cliente.persona.cliente_persona.entities.Client;
import com.microservicio.cliente.persona.cliente_persona.models.ClientDTO;
import com.microservicio.cliente.persona.cliente_persona.models.ClientInputDTO;
import com.microservicio.cliente.persona.cliente_persona.models.ResponseVo;
import com.microservicio.cliente.persona.cliente_persona.services.ClientService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/clients/")
@Validated
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClienteById(@PathVariable Long id) {
        ClientDTO cliente = clientService.getClientById(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        List<ClientDTO> list = clientService.getAllClients();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseVo(true, "", list));
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveClient(@RequestBody @Valid ClientInputDTO clientInputDTO, BindingResult result) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseVo(true, "Registered customer", clientService.saveClient(clientInputDTO)));
    }

    @PostMapping("/register-with-account")
    public ResponseEntity<?> saveClientWithAccount(@RequestBody @Valid ClientInputDTO clientInputDTO, BindingResult result) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseVo(true, "Registered customer with account", clientService.saveClientWithAccount(clientInputDTO)));
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateClientById(@PathVariable Long id,@RequestBody Map<String, Object> actualizaciones, BindingResult result) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseVo(true, "Updated Client", clientService.updateClient(actualizaciones, id)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteClientById(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseVo(true, String.format("Deleted Client : %s", id)));
    }

    @GetMapping("client/{clientId}")
    public ResponseEntity<?> getNameClientById(@PathVariable Long clientId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(clientService.getNameClientById(clientId));
    }

    @GetMapping("client/")
    public List<Long> getAllIdClients() {
        return clientService.getAllIdClients();
    }
}
