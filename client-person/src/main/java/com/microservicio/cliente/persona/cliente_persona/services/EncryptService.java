package com.microservicio.cliente.persona.cliente_persona.services;

public interface EncryptService {
    /**
     * let us encrypt the password
     * @param password
     * @return
     */
    String encryptPassword(String password);

    boolean verifyPassword(String originalPassword, String hashPassword);
}
