package com.example.mitrasoftauthservice.service;

import com.example.mitrasoftauthservice.auth.AuthenticationRequest;
import com.example.mitrasoftauthservice.auth.AuthenticationResponse;
import com.example.mitrasoftauthservice.domain.User;

import java.util.List;

public interface AuthService {

 //   List<User> getAllUsers();

    AuthenticationResponse authenticate(AuthenticationRequest request);


}
