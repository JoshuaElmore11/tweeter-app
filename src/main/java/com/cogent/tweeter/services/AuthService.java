package com.cogent.tweeter.services;

import com.cogent.tweeter.payloads.LoginPayload;
import com.cogent.tweeter.payloads.LoginResponse;
import com.cogent.tweeter.payloads.RegisterPayload;
import com.cogent.tweeter.payloads.RegisterResponse;

public interface AuthService {
    public RegisterResponse register(RegisterPayload registerPayload);

    public LoginResponse login(LoginPayload loginPayload);
}
