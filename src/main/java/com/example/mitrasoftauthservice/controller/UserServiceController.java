package com.example.mitrasoftauthservice.controller;

import com.example.mitrasoftauthservice.dto.UserDto;
import com.example.mitrasoftauthservice.service.WebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/service")
public class UserServiceController {

    private final WebClientService webClientService;

    @GetMapping("user/get")
    public List<UserDto> getAllUsers() {
        return webClientService.getAllUsers();
    }

    @GetMapping("user/get/{email}")
    public ResponseEntity<?> getUserDtoByEmail(@PathVariable String email) {

        UserDto userDto = webClientService.getUserDtoByEmail(email);
        return ResponseEntity.ok(userDto);
    }
}


