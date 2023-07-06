package com.example.mitrasoftauthservice.service;

import com.example.mitrasoftauthservice.domain.UserRole;
import com.example.mitrasoftauthservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebClientServiceImpl implements WebClientService {
    private final WebClient webClient;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getAllUsers() {
        return webClient.get()
                .uri("/api/v1/get")
                .retrieve()
                .bodyToFlux(UserDto.class)
                .collectList()
                .block();
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return webClient.get()
                .uri("/api/v1/get/{email}", email)
                .retrieve()
                .onStatus(HttpStatus::isError, response ->
                        Mono.error(new IllegalStateException("wrong email: " + email)))
                .bodyToMono(UserDto.class)
                .block();

    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return webClient.post()
                .uri("/api/v1")
                .body(Mono.just(userDto), UserDto.class)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }

    @Override
    public void deleteUser(String email) {
        webClient.delete()
                .uri("/api/v1/delete/{email}", email)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    @Override
    public UserDto updateUser(String email, UserDto userDto) {
        if (userDto.getPassword() != null) {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        return webClient.post()
                .uri("/api/v1/update/{email}", email)
                .body(Mono.just(userDto), UserDto.class)
                .retrieve()
                .onStatus(HttpStatus::isError, response ->
                        Mono.error(new IllegalStateException("wrong email: " + email)))
                .bodyToMono(UserDto.class)
                .block();
    }

    @Override
    public UserDto changeRole(String email, UserRole userRole) {
        return webClient.post()
                .uri("/api/v1/role/{email}", email)
                .body(Mono.just(userRole), UserRole.class)
                .retrieve()
                .onStatus(HttpStatus::isError, response ->
                        Mono.error(new IllegalStateException("wrong email: " + email)))
                .bodyToMono(UserDto.class)
                .block();
    }
}