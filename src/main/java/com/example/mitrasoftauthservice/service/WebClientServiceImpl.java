package com.example.mitrasoftauthservice.service;

import com.example.mitrasoftauthservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebClientServiceImpl implements WebClientService {
    private final WebClient webClient;

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
    public UserDto getUserDtoByEmail(String email) {
        return webClient.get()
                .uri("/api/v1/get/{email}", email)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();

    }

}




