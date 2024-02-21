package com.example.apteka.controllers;

import com.example.apteka.dtos.JwtRequest;
import com.example.apteka.dtos.RegistrationUserDto;
import com.example.apteka.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping(value = "/auth",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        log.info("Received login request for user: {}", authRequest.getFirstname());
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto){
        return authService.createNewUser(registrationUserDto);
        }
}
