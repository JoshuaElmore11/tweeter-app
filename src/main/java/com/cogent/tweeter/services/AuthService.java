package com.cogent.tweeter.services;

import com.cogent.tweeter.entities.User;
import com.cogent.tweeter.payloads.*;

import java.util.List;

public interface AuthService {
    RegisterResponse register(RegisterPayload registerPayload);

    LoginResponse login(LoginPayload loginPayload);
    List<User> getAllUsers();
    UserResponse getUser(String name);
}
