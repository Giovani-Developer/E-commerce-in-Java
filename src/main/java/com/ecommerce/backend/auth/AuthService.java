package com.ecommerce.backend.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ecommerce.backend.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String register(String email, String nome, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encoder.encode(password));
        user.setRole("USER");

        userRepository.save(user);
        return jwtService.generateToken(email);
    }

    public String login(String email, String passoword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

                if (!encoder.matches(passoword, user.getPassword())){
                    throw new RuntimeException("Senha inválida.");
                }

                return jwtService.generateToken(email);
    }
}