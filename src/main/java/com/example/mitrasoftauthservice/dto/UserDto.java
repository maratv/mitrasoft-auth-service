package com.example.mitrasoftauthservice.dto;

import com.example.mitrasoftauthservice.domain.UserRole;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private String email;

    private UserRole role;

    private String password;

    private String firstName;

    private String lastName;

    private LocalDate birthday;
}
