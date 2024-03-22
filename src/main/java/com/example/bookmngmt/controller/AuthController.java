package com.example.bookmngmt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmngmt.dto.UserRequest;
import com.example.bookmngmt.service.JwtTokenService;
import com.example.bookmngmt.service.LoginService;

import lombok.RequiredArgsConstructor;

@RestController()
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final JwtTokenService jwtTokenService;
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequest userRequest) {
        return loginService.login(userRequest)
                .map(userId -> {
                    String token = jwtTokenService.generateToken(userId, "USER");
                    return new ResponseEntity<>("Bearer " + token, HttpStatus.OK);
                }).orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }
}
