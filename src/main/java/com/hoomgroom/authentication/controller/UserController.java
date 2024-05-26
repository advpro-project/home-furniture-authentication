package com.hoomgroom.authentication.controller;

import com.hoomgroom.authentication.dto.UserData;
import com.hoomgroom.authentication.dto.UserDataBuilder;
import com.hoomgroom.authentication.model.User;
import com.hoomgroom.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<UserData> getUserByEmail (@PathVariable("email") String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            UserData userData = new UserDataBuilder()
                    .fullName(user.get().getFullName())
                    .dateOfBirth(user.get().getDateOfBirth())
                    .gender(user.get().getGender())
                    .username(user.get().getUsername())
                    .email(user.get().getEmail())
                    .role(user.get().getRole())
                    .build();
            return ResponseEntity.ok(userData);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserData());
        }
    }
}
