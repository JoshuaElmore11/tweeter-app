package com.cogent.tweeter.controllers;

import com.cogent.tweeter.payloads.LoginPayload;
import com.cogent.tweeter.payloads.LoginResponse;
import com.cogent.tweeter.payloads.RegisterPayload;
import com.cogent.tweeter.payloads.RegisterResponse;
import com.cogent.tweeter.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginPayload loginPayload) {
        var response = authService.login(loginPayload);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterPayload registerPayload) {
        var response = authService.register(registerPayload);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
