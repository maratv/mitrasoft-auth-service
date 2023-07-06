package com.example.mitrasoftauthservice.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.mitrasoftauthservice.domain.UserRole;
import com.example.mitrasoftauthservice.dto.UserDto;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

@ContextConfiguration(classes = {WebClientServiceImpl.class})
@ExtendWith(SpringExtension.class)
class WebClientServiceImplTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private WebClient webClient;

    @Autowired
    private WebClientServiceImpl webClientServiceImpl;

    @Test
    void testGetAllUsers() {
        Mockito.<WebClient.RequestHeadersUriSpec<?>>when(webClient.get())
                .thenThrow(new IllegalStateException("/api/v1/get"));
        assertThrows(IllegalStateException.class, () -> webClientServiceImpl.getAllUsers());
        verify(webClient).get();
    }

    @Test
    void testGetUserByEmail() {
        Mockito.<WebClient.RequestHeadersUriSpec<?>>when(webClient.get())
                .thenThrow(new IllegalStateException("/api/v1/get/{email}"));
        assertThrows(IllegalStateException.class, () -> webClientServiceImpl.getUserByEmail("petr@bk.ru"));
        verify(webClient).get();
    }

    @Test
    void testCreateUser() {
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenThrow(new IllegalStateException("/api/v1"));

        UserDto userDto = new UserDto();
        userDto.setBirthday(LocalDate.of(1992, 2, 3));
        userDto.setEmail("petr@bk.ru");
        userDto.setFirstName("Petr");
        userDto.setLastName("Petrov");
        userDto.setPassword("pass");
        userDto.setRole(UserRole.ADMIN);
        assertThrows(IllegalStateException.class, () -> webClientServiceImpl.createUser(userDto));
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    @Test
    void testDeleteUser() {
        Mockito.<WebClient.RequestHeadersUriSpec<?>>when(webClient.delete())
                .thenThrow(new IllegalStateException("/api/v1/delete/{email}"));
        assertThrows(IllegalStateException.class, () -> webClientServiceImpl.deleteUser("petr@bk.ru"));
        verify(webClient).delete();
    }

    @Test
    void testUpdateUser() {
        when(passwordEncoder.encode(Mockito.<CharSequence>any()))
                .thenThrow(new IllegalStateException("/api/v1/update/{email}"));

        UserDto userDto = new UserDto();
        userDto.setBirthday(LocalDate.of(1992, 2, 3));
        userDto.setEmail("petr@bk.ru");
        userDto.setFirstName("Petr");
        userDto.setLastName("Petrov");
        userDto.setPassword("pass");
        userDto.setRole(UserRole.ADMIN);
        assertThrows(IllegalStateException.class, () -> webClientServiceImpl.updateUser("petr@bk.ru", userDto));
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    @Test
    void testChangeRole() {
        when(webClient.post()).thenThrow(new IllegalStateException("/api/v1/role/{email}"));
        assertThrows(IllegalStateException.class,
                () -> webClientServiceImpl.changeRole("petr@bk.ru", UserRole.ADMIN));
        verify(webClient).post();
    }

}

