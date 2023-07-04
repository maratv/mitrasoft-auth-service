package com.example.mitrasoftauthservice.config;

import com.example.mitrasoftauthservice.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class WebClientAuthenticationProvider implements AuthenticationProvider {

    private final UserClient userClient;
    private final WebClientUserDetailsService webClientUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        User user = userClient.getUserByEmail(authentication.getName());

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (user != null && user.getEmail().equals(name) && user.getPassword().equals(password)) {
            return new UsernamePasswordAuthenticationToken(
                    name, password, new ArrayList<>());
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
