package com.hoomgroom.authentication.service;

import com.hoomgroom.authentication.model.User;
import com.hoomgroom.authentication.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByUsername(String username) {
        if (username == null || username.isEmpty()) {
            LOGGER.warn("Invalid username provided for findByUsername");
            return Optional.empty();
        }
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        if (email == null || email.isEmpty()) {
            LOGGER.warn("Invalid email provided for findByEmail");
            return Optional.empty();
        }
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        if (user == null) {
            LOGGER.warn("Null user object provided for saveUser");
            return null;
        }

        // Validate user input
        if (!isValidUser(user)) {
            LOGGER.warn("Invalid user object provided for saveUser: {}", user);
            return null;
        }

        // Check for existing user with the same username
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            LOGGER.warn("Username already exists: {}", user.getUsername());
            return null;
        }

        // Check for existing user with the same email
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            LOGGER.warn("Email already exists: {}", user.getEmail());
            return null;
        }

        // Encode password and save user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean authenticate(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            LOGGER.warn("Invalid username or password provided for authenticate");
            return false;
        }

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    @Override
    public boolean isValidUser(User user) {
        return user != null &&
                user.getUsername() != null && !user.getUsername().isEmpty() &&
                user.getEmail() != null && !user.getEmail().isEmpty() &&
                user.getPassword() != null && !user.getPassword().isEmpty();
    }
}
