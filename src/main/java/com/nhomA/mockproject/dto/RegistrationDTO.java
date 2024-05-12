package com.nhomA.mockproject.dto;

import jakarta.validation.constraints.Email;

import java.time.LocalDate;

public class RegistrationDTO {


    @Email(message = "Invalid email format")
    private String username;

    //    private String username;
    private String password;
//    private String fullName;
//    private String phone;

    public RegistrationDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}