package com.example.mitrasoftauthservice.service;

import com.example.mitrasoftauthservice.domain.UserRole;
import com.example.mitrasoftauthservice.dto.UserDto;

import java.util.List;

public interface WebClientService {

    List<UserDto> getAllUsers();

    UserDto getUserByEmail(String email);

    UserDto createUser(UserDto userDto);

    void deleteUser(String email);

    UserDto updateUser(String email, UserDto userDto);

    UserDto changeRole(String email, UserRole userRole);

}
