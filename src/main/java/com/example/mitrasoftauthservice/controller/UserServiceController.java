package com.example.mitrasoftauthservice.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/service")
public class UserServiceController {

    @GetMapping("/admin")
    public String admin() {
        return "for admin";
    }


    @GetMapping("/user")
    public String user() {
        return "for user";
    }

}
