package com.example.mitrasoftauthservice.controller;

import com.example.mitrasoftauthservice.domain.UserRole;
import com.example.mitrasoftauthservice.dto.UserDto;
import com.example.mitrasoftauthservice.service.WebClientService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserServiceController {

    private final WebClientService webClientService;

    @Operation(summary = "Get all users",
            description = "Get all users")
    @GetMapping("/user/get")
    public List<UserDto> getAllUsers() {
        return webClientService.getAllUsers();
    }

    @Operation(summary = "Get user by email",
            description = "Get user by email")
    @GetMapping("/user/get/{email}")
    public ResponseEntity<?> getUserDtoByEmail(@PathVariable String email) {
        return ResponseEntity.ok(webClientService.getUserByEmail(email));
    }

    @Operation(summary = "Create new user",
            description = "Create new user")
    @PostMapping("/admin")
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(webClientService.createUser(userDto));
    }

    @Operation(summary = "Delete user",
            description = "Delete user")
    @DeleteMapping("/admin/delete/{email}")
    public ResponseEntity<?> delete(@PathVariable String email) {
        webClientService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update user",
            description = "Update user")
    @PostMapping("/admin/update/{email}")
    public ResponseEntity<?> updateUser(@PathVariable String email, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(webClientService.updateUser(email, userDto));
    }

    @Operation(summary = "Change Role",
            description = "Change Role")
    @PostMapping("/admin/role/{email}")
    public ResponseEntity<?> changeRole(@PathVariable String email, @RequestBody UserRole userRole) {
        return ResponseEntity.ok(webClientService.changeRole(email, userRole));
    }
}