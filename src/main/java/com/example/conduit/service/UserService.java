package com.example.conduit.service;

import com.example.conduit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User register(String email, String rawPassword, String username) {
        var encodedPassword = passwordEncoder.encode(rawPassword);
        return userRepository.save(new User(null, username, encodedPassword, email));
    }

}
