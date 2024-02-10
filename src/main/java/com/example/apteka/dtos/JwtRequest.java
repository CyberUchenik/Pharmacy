package com.example.apteka.dtos;

import lombok.Data;

@Data
public class JwtRequest {
    private String firstname;
    private String password;
}
