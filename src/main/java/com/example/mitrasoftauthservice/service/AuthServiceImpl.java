package com.example.mitrasoftauthservice.service;

import com.example.mitrasoftauthservice.auth.AuthenticationRequest;
import com.example.mitrasoftauthservice.auth.AuthenticationResponse;
import com.example.mitrasoftauthservice.config.JwtService;
import com.example.mitrasoftauthservice.config.UserClient;
import com.example.mitrasoftauthservice.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

       private final WebClient webClient;
    private final WebClientService webClientService;
    private final UserClient userClient;

    //  private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

//    @Override
//    public List<User> getAllUsers() {
//        return webClient.get()
//                .uri("/api/v1/get")
//                .retrieve()
//                .bodyToFlux(User.class)
//                .toStream().toList();
//
//    }

  //  @Override
    public AuthenticationResponse authenticate1(AuthenticationRequest request) {

//        User user = userClient.getUserByEmail(request.getEmail());
     //   User user = webClientService.getUserByEmail(request.getEmail());
//        if (user == null) {
//            throw new UsernameNotFoundException("Пользователь не найден: " + request.getEmail());
//        }
//        if (user.getEmail().equals(request.getEmail()) && user.getPassword().equals(request.getPassword())) {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

//        User user = userClient.getUserByEmail(request.getEmail());
//        if (user == null) {
//            throw new UsernameNotFoundException("Пользователь не найден: " + request.getEmail());
//        }

        User user = userClient.getUserByEmail(request.getEmail());
        //      User user = webClientService.getUserByEmail(request.getEmail());
            System.out.println("user" + user);

            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
//        } else {
//            return null;
//        }
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

//        User user = userClient.getUserByEmail(request.getEmail());
        User user = webClientService.getUserByEmail(request.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден: " + request.getEmail());
        }
        if (user.getEmail().equals(request.getEmail()) && user.getPassword().equals(request.getPassword())) {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

//        User user = userClient.getUserByEmail(request.getEmail());
//        if (user == null) {
//            throw new UsernameNotFoundException("Пользователь не найден: " + request.getEmail());
//        }


            //  User user = webClientService.getUserByEmail(request.getEmail());
            System.out.println("user" + user);

            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            return null;
        }
    }






}


//    webClient.get()
//            .uri("resource")
//        .retrieve()
//        .bodyToMono(String.class)
//        .subscribe(response -> {
//        // Process the response
//    });
//
//webClient.get()
//        .uri("resource")
//        .header("Authorization", "Bearer your_token")
//        .retrieve()
//        .bodyToMono(String.class)
//        .subscribe(response -> {
//        // Process the response
//    });





