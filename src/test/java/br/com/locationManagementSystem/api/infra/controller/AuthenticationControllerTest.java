package br.com.locationManagementSystem.api.infra.controller;

import br.com.locationManagementSystem.api.infra.controller.AuthenticationController;
import br.com.locationManagementSystem.api.infra.controller.Dtos.Security.AuthenticationDto;
import br.com.locationManagementSystem.api.infra.controller.Dtos.Security.TokenDto;
import br.com.locationManagementSystem.api.infra.controller.Dtos.Security.TokenRefreshDto;
import br.com.locationManagementSystem.api.infra.persistence.Authentication.UserEntity;
import br.com.locationManagementSystem.api.infra.persistence.Authentication.UserRepositoryInfra;
import br.com.locationManagementSystem.api.infra.security.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthenticationControllerTest {

    private AuthenticationController authenticationController;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    private UserRepositoryInfra userRepositoryInfra;

    @BeforeEach
    void setUp() {
        authenticationManager = mock(AuthenticationManager.class);
        tokenService = mock(TokenService.class);
        userRepositoryInfra = mock(UserRepositoryInfra.class);
        authenticationController = new AuthenticationController(userRepositoryInfra, authenticationManager, tokenService);
    }

    @Test
    void testExecuteLoginSuccess() {
        // Given
        AuthenticationDto authDto = new AuthenticationDto("user", "pass");
        UserEntity userEntity = new UserEntity("user", "user@example.com", "John", "Doe", "encodedPass");
        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userEntity);
        when(tokenService.generateToken(userEntity)).thenReturn("accessToken");
        when(tokenService.generateRefreshToken(userEntity)).thenReturn("refreshToken");

        // When
        ResponseEntity<TokenDto> response = authenticationController.executeLogin(authDto);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(new TokenDto("accessToken", "refreshToken"), response.getBody());
    }

    @Test
    void testExecuteLoginFailure() {
        // Given
        AuthenticationDto authDto = new AuthenticationDto("user", "wrongPass");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Authentication failed"));

        // When
        ResponseEntity<TokenDto> response = authenticationController.executeLogin(authDto);

        // Then
        assertEquals(401, response.getStatusCodeValue()); // Unauthorized is more appropriate for failed authentication
    }

    @Test
    void testRefreshAccessTokenSuccess() {
        // Given
        TokenRefreshDto tokenRefreshDto = new TokenRefreshDto("refreshToken");
        UserEntity userEntity = new UserEntity("user", "user@example.com", "John", "Doe", "encodedPass");

        when(tokenService.getSubject("refreshToken")).thenReturn("user");
        when(userRepositoryInfra.findByUsernameEntity("user")).thenReturn(java.util.Optional.of(userEntity));
        when(tokenService.generateToken(userEntity)).thenReturn("newAccessToken");
        when(tokenService.generateRefreshToken(userEntity)).thenReturn("newRefreshToken");

        // When
        ResponseEntity<Object> response = authenticationController.refreshAccessToken(tokenRefreshDto);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(new TokenDto("newAccessToken", "newRefreshToken"), response.getBody());
    }

    @Test
    void testRefreshAccessTokenFailure() {
        // Given
        TokenRefreshDto tokenRefreshDto = new TokenRefreshDto("invalidRefreshToken");
        when(tokenService.getSubject("invalidRefreshToken")).thenReturn("user");
        when(userRepositoryInfra.findByUsernameEntity("user")).thenReturn(java.util.Optional.empty());

        // When
        ResponseEntity<Object> response = authenticationController.refreshAccessToken(tokenRefreshDto);

        // Then
        assertEquals(400, response.getStatusCodeValue()); // Bad Request is appropriate for invalid refresh token
        assertEquals("Invalid refresh token", response.getBody());
    }
}

