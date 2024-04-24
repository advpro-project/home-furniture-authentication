package com.hoomgroom.authentication.service;

import com.hoomgroom.authentication.model.User;
import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    User saveUser(User user);

    boolean authenticate(String username, String password);

    boolean isValidUser(User user);
}
