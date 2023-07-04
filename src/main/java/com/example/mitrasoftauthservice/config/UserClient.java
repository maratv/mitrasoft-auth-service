package com.example.mitrasoftauthservice.config;

import com.example.mitrasoftauthservice.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Component
public class UserClient {

    private final WebClient webClient;

    public User getUserByEmail(Object email) {
        return webClient.get()
                .uri("/api/v1/auth/{email}", email)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

}
