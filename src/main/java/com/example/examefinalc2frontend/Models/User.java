package com.example.examefinalc2frontend.Models;

import lombok.Data;

@Data
public class User {
    private int id;
    private String email;
    private String password;
    private int roleId;
    private boolean enabled;
    private boolean accountNonLocked;
    private String username;
    private boolean credentialsNonExpired;
    private boolean accountNonExpired;
    private Employee employee;
}