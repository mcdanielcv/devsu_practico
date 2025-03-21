package com.microservicio.cliente.persona.cliente_persona.repositories;

import com.microservicio.cliente.persona.cliente_persona.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT a.client_Id FROM Client a", nativeQuery = true)
    List<Integer> findAllIdClients();
}
