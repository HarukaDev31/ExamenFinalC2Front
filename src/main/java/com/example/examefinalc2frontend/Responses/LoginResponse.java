package com.example.examefinalc2frontend.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private boolean success;
    private String token;
    private String error;
    private String message;
    private String username;

    // Constructor para casos de error
    public LoginResponse(String error) {
        this.success = false;
        this.error = error;
        this.message = error;
    }
}