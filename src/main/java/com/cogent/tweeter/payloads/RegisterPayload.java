package com.cogent.tweeter.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterPayload {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
