package com.example.examefinalc2frontend.Exception.User;

import com.example.examefinalc2frontend.Exception.ApiException;

public class UserNotFoundException extends ApiException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
