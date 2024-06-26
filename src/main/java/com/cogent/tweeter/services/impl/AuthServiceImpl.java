package com.cogent.tweeter.services.impl;

import com.cogent.tweeter.entities.Role;
import com.cogent.tweeter.entities.User;
import com.cogent.tweeter.payloads.*;
import com.cogent.tweeter.repositories.RoleRepository;
import com.cogent.tweeter.repositories.UserRepository;
import com.cogent.tweeter.security.JwtTokenProvider;
import com.cogent.tweeter.services.AuthService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public RegisterResponse register(RegisterPayload registerPayload) {
        //check username
        if(userRepository.existsByUsername(registerPayload.getUserName())){
            throw new RuntimeException("Username already exists");
        }
        //check email
        if(userRepository.existsByEmail(registerPayload.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        //create blank user
        User user = new User();
        //generate UUID for user_id
        user.setId(UUID.randomUUID());
        //set user values from payload
        user.setUsername(registerPayload.getUserName());
        user.setPassword(passwordEncoder.encode(registerPayload.getPassword()));
        user.setFirstName(registerPayload.getFirstName());
        user.setLastName(registerPayload.getLastName());
        user.setEmail(registerPayload.getEmail());
        user.setPhoneNumber(registerPayload.getPhoneNumber());

        //create set to store user roles and add to user
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("user").get();
        roles.add(userRole);
        user.setRoles(roles);

        //save user
        userRepository.save(user);

        //create and send response payload
        RegisterResponse response = new RegisterResponse();
        response.setMessage("User created successfully");

        return response;
    }

    @Override
    public LoginResponse login(LoginPayload loginPayload) {
        User user = userRepository.findByUsernameOrEmail(loginPayload.getUsernameOrEmail(),loginPayload.getUsernameOrEmail()).get();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginPayload.getUsernameOrEmail(), loginPayload.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        LoginResponse response = new LoginResponse();
        response.setMessage("Login successful");
        response.setToken(jwtTokenProvider.generateToken(authentication));
        response.setUserName(user.getUsername());

        return response;
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public UserResponse getUser(String name) {
        User user = userRepository.findByUsername(name).get();
        UserResponse response = new UserResponse();

        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setUserName(name);

        return response;
    }
}
