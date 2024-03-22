package com.example.bookmngmt.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    
    @NotEmpty(message = "{required.field}")
    private String username;
    @NotEmpty(message = "{required.field}")
    private String password;
}
