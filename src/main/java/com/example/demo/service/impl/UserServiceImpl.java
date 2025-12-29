package com.example.demo.service.impl;

import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

public class UserServiceImpl {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository r, BCryptPasswordEncoder e, JwtUtil j) {
        this.repo = r;
        this.encoder = e;
        this.jwtUtil = j;
    }

    public User register(User user) {
        if (user == null) throw new RuntimeException();
        if (repo.existsByEmail(user.getEmail())) throw new RuntimeException();
        if (user.getRole() == null) user.setRole("LEARNER");
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public AuthResponse login(String email, String password) {
        User u = repo.findByEmail(email).orElseThrow(RuntimeException::new);
        if (!encoder.matches(password, u.getPassword())) throw new RuntimeException();

        String token = jwtUtil.generateToken(new HashMap<>(), email);
        return new AuthResponse(token, u.getId(), u.getEmail(), u.getRole());
    }

    public User findByEmail(String email) {
        return repo.findByEmail(email).orElseThrow(RuntimeException::new);
    }
}
