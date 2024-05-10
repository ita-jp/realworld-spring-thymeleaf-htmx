package com.example.conduit.service;

import com.example.conduit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User register(String email, String rawPassword, String username) {
        var encodedPassword = passwordEncoder.encode(rawPassword);
        return userRepository.save(new User(null, username, encodedPassword, email, null, null));
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void update(
            long id,
            String email,
            String password,
            String username,
            String bio,
            String image
    ) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (Strings.isNotEmpty(email)) {
            user.setEmail(email);
        }
        if (Strings.isNotEmpty(username)) {
            user.setUsername(username);
        }
        if (Strings.isNotEmpty(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }
        if (Strings.isNotEmpty(bio)) {
            user.setBio(bio);
        }
        if (Strings.isNotEmpty(image)) {
            user.setImage(image);
        }

        userRepository.save(user);
    }
}
