package com.loja_virtual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loja_virtual.dtos.LoginRequest;
import com.loja_virtual.dtos.LoginResponse;
import com.loja_virtual.services.AuthService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200") // Permite requisições do Angular
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @PostMapping // Removido "/login" para evitar duplicação na URL
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        logger.info("🔵 Requisição recebida do front-end: {}", request);
        logger.info("🔵 Requisição recebida: Username = {}, Password = {}", request.getUsername(), request.getPassword());

        LoginResponse response = authService.authenticate(request);

        if (response != null) {
            logger.info("🟢 Login bem-sucedido. Enviando token...");
            return ResponseEntity.ok(response);
        } else {
            
            logger.warn("🔴 Falha na autenticação. Usuário ou senha inválidos.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
