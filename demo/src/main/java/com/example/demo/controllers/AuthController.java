package com.example.demo.controllers;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entities.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody User user){
        userService.register(user);
        return new ApiResponse<>(true, 201, "User registered successfully", null);
    }

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody User user){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        } catch (BadCredentialsException e){
            return new ApiResponse<>(false, 401, "Invalid credentials", null);
        }

        final String token = jwtUtil.generateToken(user.getUsername());
        return new ApiResponse<>(true, 200, "Login successful", token);
    }
}