package com.hoomgroom.authentication.service;

import com.hoomgroom.authentication.model.User;
import com.hoomgroom.authentication.model.UserBuilder;
import com.hoomgroom.authentication.repository.UserRepository;
import enums.Gender;
import enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
public class SeedService {
    @Autowired
    private UserRepository userRepository;

    private static final int SEED_COUNT = 10_000;
    private final Gender[] genders = {Gender.MALE, Gender.FEMALE};
    private final Role[] roles = {Role.ADMIN, Role.PEMBELI};
    private final Random random = new Random();

    @Async("asyncExecutor")
    public CompletableFuture<Boolean> seed() {
        for (int i = 0; i < SEED_COUNT; i++) {
            String fullname = "User " + i;
            LocalDate dateOfBirth = LocalDate.now().minusYears(random.nextInt(50));
            Gender gender = genders[random.nextInt(genders.length)];
            String username = "user" + i;
            String email = "user" + i + "@gmail.com";
            String password = "password" + i;
            Role role = roles[random.nextInt(roles.length)];
            double walletBalance = random.nextDouble() * 1_000_000;

            try {
                User user = new UserBuilder()
                        .fullName(fullname)
                        .dateOfBirth(dateOfBirth)
                        .gender(gender)
                        .username(username)
                        .email(email)
                        .password(password)
                        .role(role)
                        .walletBalance(walletBalance)
                        .build();
                userRepository.save(user);
            } catch (IllegalArgumentException e) {
                return CompletableFuture.completedFuture(false);
            }
        }
        return CompletableFuture.completedFuture(true);
    }
}
