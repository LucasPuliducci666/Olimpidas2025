package com.example.olimpiadas25.persistence.entity;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}