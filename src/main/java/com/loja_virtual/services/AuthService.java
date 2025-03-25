package com.loja_virtual.services;

import com.loja_virtual.dtos.LoginRequest;
import com.loja_virtual.dtos.LoginResponse;
import com.loja_virtual.model.User;
import com.loja_virtual.repository.UserRepository;
import com.loja_virtual.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    /* 
    public LoginResponse authenticate(LoginRequest request) {
        // Aqui você pode validar as credenciais com o banco de dados
        if ("admin@email.com".equals(request.getUsername()) && "123456".equals(request.getPassword())) {
            // Retorna um token JWT (simulado por enquanto)
            return new LoginResponse("token_fake_123");
        }
        throw new RuntimeException("Credenciais inválidas!");
    }
    */
    @Autowired
    private User user;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse authenticate(LoginRequest request) throws Exception{
        // Busca o usuário pelo username
     user = userRepository.findByUsername(request.getUsername())
    .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

    // Verifica se a senha informada corresponde à senha armazenada (criptografada)
    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("Senha inválida!");
    }

    // Se estiver tudo certo, gera um token JWT
    String token = jwtUtil.generateToken(user.getUsername());

    return new LoginResponse(token);
}


}
