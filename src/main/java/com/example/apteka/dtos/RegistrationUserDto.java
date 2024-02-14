package com.example.apteka.dtos;

import lombok.Data;

//TODO надо написать логику для регистрации
@Data
public class RegistrationUserDto {

    private String firstname;
    private String lastname;
    private String fathername;
    private String login;
    private String email;
    private String phone;
    private String hashed_password;
    private String confirmHashed_password;
    private String def;

}
