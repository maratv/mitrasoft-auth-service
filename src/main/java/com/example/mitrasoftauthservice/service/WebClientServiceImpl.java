package com.example.mitrasoftauthservice.service;

import com.example.mitrasoftauthservice.auth.AuthenticationRequest;
import com.example.mitrasoftauthservice.domain.User;
import com.example.mitrasoftauthservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WebClientServiceImpl implements WebClientService{
    private final WebClient webClient;

    @Override
    public User checkUserCredentials(AuthenticationRequest request) {
        return webClient.get()

                .uri("/api/v1/ge", request)

                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    public List<UserDto> getUsername() {
        return webClient.get()

                .uri("/api/v1/get")

                .retrieve()
                .bodyToFlux(UserDto.class)
                .collectList().block();
    }

    public Mono<String> findByEmail(String email) {
        return webClient.get()

                .uri("/api/v1/get", email)

                .retrieve()
                .bodyToMono(String.class);
              //  .block();
    }

    public Optional<String> getByEmail(Mono<String> stringMono) {
        Optional<String> result = findByEmail("sa").blockOptional();
        return result;
    }

    @Override
    public User getByEmail2(String email) {
        return webClient.get()

                .uri("/api/v1/get", email)

                .retrieve()
                .bodyToMono(User.class)
                .block();


    }

    @Override
    public User getUserByEmail(String email) {
        return webClient.get()
                .uri("/api/v1/get/{email}", email)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    @Override
    public String adminAccess() {
        return webClient.get()
                .uri("/api/v1/get/")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public String userAccess() {
        return null;
    }

}
