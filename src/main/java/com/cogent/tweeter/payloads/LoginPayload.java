package com.cogent.tweeter.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginPayload {
    private String usernameOrEmail;
    private String password;
}
