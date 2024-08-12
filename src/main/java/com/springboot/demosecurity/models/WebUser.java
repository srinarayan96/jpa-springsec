package com.springboot.demosecurity.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WebUser {
    @NotBlank(message = "username cannot be empty")
    private String userName;
    @NotBlank(message = "password cannot be empty")
    private String password;
    @NotBlank(message = "first name cannot be empty")
    private String firstName;
    private String lastName;
    @NotBlank(message = "email cannot be empty")
    private String email;
}
