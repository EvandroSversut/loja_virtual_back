package com.loja_virtual.services;

import com.loja_virtual.dtos.LoginRequest;
import com.loja_virtual.dtos.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public LoginResponse authenticate(LoginRequest request) {
        // Aqui você pode validar as credenciais com o banco de dados
        if ("admin@email.com".equals(request.getUsername()) && "123456".equals(request.getPassword())) {
            // Retorna um token JWT (simulado por enquanto)
            return new LoginResponse("token_fake_123");
        }
        throw new RuntimeException("Credenciais inválidas!");
    }
}
