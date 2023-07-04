package com.example.mitrasoftauthservice.service;

import com.example.mitrasoftauthservice.dto.UserDto;

import java.util.List;

public interface WebClientService {

    List<UserDto> getAllUsers();

    UserDto getUserDtoByEmail(String email);



}
