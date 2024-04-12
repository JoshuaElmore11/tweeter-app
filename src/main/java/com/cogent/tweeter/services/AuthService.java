package com.cogent.tweeter.services;

import com.cogent.tweeter.entities.User;
import com.cogent.tweeter.payloads.LoginPayload;
import com.cogent.tweeter.payloads.LoginResponse;
import com.cogent.tweeter.payloads.RegisterPayload;
import com.cogent.tweeter.payloads.RegisterResponse;

import java.util.List;

public interface AuthService {
    RegisterResponse register(RegisterPayload registerPayload);

    LoginResponse login(LoginPayload loginPayload);
    List<User> getAllUsers();
}
