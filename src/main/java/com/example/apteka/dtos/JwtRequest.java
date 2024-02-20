package com.example.apteka.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtRequest {
    private String firstname;
    private String password;

    public String getFirstname() {
        return firstname;
    }

    public String getPassword() {
        return password;
    }
}

