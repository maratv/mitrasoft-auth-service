package com.example.mitrasoftauthservice.service;

import com.example.mitrasoftauthservice.auth.AuthenticationRequest;
import com.example.mitrasoftauthservice.domain.User;

public interface WebClientService {

    User checkUserCredentials(AuthenticationRequest request);

    User getByEmail2(String email);

    User getUserByEmail(String email);

    String adminAccess();

    String userAccess();



}
