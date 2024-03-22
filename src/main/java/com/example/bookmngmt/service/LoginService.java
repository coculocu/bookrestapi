package com.example.bookmngmt.service;

import org.springframework.stereotype.Service;

import com.example.bookmngmt.dto.UserRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginService {
    
    Map<String, String> userMap = new HashMap<>(Map.of("admin", "admin"));

    public Optional<Integer> login(UserRequest userRequest) {
        String password = userMap.get(userRequest.getUsername());

        if(password != null && password.equals(userRequest.getPassword())){
            return Optional.of(1);
        }

        return Optional.empty();
    }
}
