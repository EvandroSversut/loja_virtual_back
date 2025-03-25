package com.loja_virtual.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.loja_virtual.model.User;
import com.loja_virtual.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {

         // Verifica se o usuário já existe
         if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Usuário já cadastrado!");
        }

        // Criptografa a senha antes de salvar
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
