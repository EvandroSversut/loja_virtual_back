package com.loja_virtual.controllers;
/* 
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
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja_virtual.util.JwtUtil;

@RestController
@RequestMapping("/auth") // Define a rota base para os endpoints deste controlador
@CrossOrigin(origins = "http://localhost:4200") // Permite chamadas do front-end Angular
public class AuthController {

     // Cria um logger para registrar mensagens no console
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private JwtUtil jwtUtil;

    // Endpoint para login, acessível via POST em /auth/login
    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest loginRequest) {

        // Imprime os dados recebidos no console para depuração
        logger.info("Dados recebidos do front-end: Username = {}, Password = {}", 
                loginRequest.getUsername(), loginRequest.getPassword());
        
        // Se preferir usar System.out.println:
         System.out.println("Username: " + loginRequest.getUsername() + " - Password: " + loginRequest.getPassword());
        

        // Aqui você pode implementar a validação das credenciais com um banco de dados.
        // Neste exemplo, usamos uma verificação fixa: usuário "user" e senha "password".
        if ("user".equals(loginRequest.getUsername()) && "password".equals(loginRequest.getPassword())) {
            // Se as credenciais forem válidas, gera um token JWT e o retorna.
            return jwtUtil.generateToken(loginRequest.getUsername());
        } else {
            // Se as credenciais forem inválidas, lança uma exceção.
            throw new RuntimeException("Usuário ou senha inválidos");
        }
    }
}

// Classe para mapear o corpo da requisição de login
class UserLoginRequest {
    private String username;
    private String password;

    // Getters e setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
