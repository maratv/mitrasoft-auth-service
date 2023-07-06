package com.example.mitrasoftauthservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.mitrasoftauthservice.auth.AuthenticationRequest;
import com.example.mitrasoftauthservice.config.JwtService;
import com.example.mitrasoftauthservice.config.UserClient;
import com.example.mitrasoftauthservice.domain.User;
import com.example.mitrasoftauthservice.domain.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AuthServiceImplTest {
    @Autowired
    private AuthServiceImpl authServiceImpl;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserClient userClient;

    @Test
    void testAuthenticate() throws AuthenticationException {
        when(userClient.getUserByEmail(Mockito.<Object>any()))
                .thenReturn(new User("petr@bk.ru", UserRole.ADMIN, "pass"));
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        assertEquals("ABC123",
                authServiceImpl.authenticate(new AuthenticationRequest("petr@bk.ru", "pass")).getToken());
        verify(userClient).getUserByEmail(Mockito.<Object>any());
        verify(jwtService).generateToken(Mockito.<UserDetails>any());
        verify(authenticationManager).authenticate(Mockito.<Authentication>any());
    }

}

