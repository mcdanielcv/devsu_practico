package com.microservicio.cliente.persona.cliente_persona.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptServiceImp implements  EncryptService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean verifyPassword(String originalPassword, String hashPassword) {
        return passwordEncoder.matches(originalPassword, hashPassword);
    }
}
