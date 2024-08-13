package br.com.locationManagementSystem.api.infra.controller;

import br.com.locationManagementSystem.api.infra.controller.Dtos.Security.TokenRefreshDto;
import br.com.locationManagementSystem.api.infra.persistence.Authentication.UserRepositoryInfra;
import br.com.locationManagementSystem.api.infra.security.TokenService;
import br.com.locationManagementSystem.api.infra.controller.Dtos.Security.AuthenticationDto;
import br.com.locationManagementSystem.api.infra.controller.Dtos.Security.TokenDto;
import br.com.locationManagementSystem.api.infra.persistence.Authentication.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/login")
public class AuthenticationController {
    public final UserRepositoryInfra userRepository;
    public final AuthenticationManager manager;
    private final TokenService tokenService;

    public AuthenticationController(UserRepositoryInfra userRepository, AuthenticationManager manager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @Operation(summary = "Authenticate user", description = "Passing a login and password that was registered before, it will return a token to authenticate and allow access to the application's resources",
            tags = {"Authentication"},
            responses = {
                    @ApiResponse(responseCode = "200", ref = "#/components/responses/Authentication200Response"),
            })
    @PostMapping
    public ResponseEntity<TokenDto> executeLogin(@RequestBody @Valid AuthenticationDto data) {
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(token);

        var tokenJWT = tokenService.generateToken((UserEntity) authentication.getPrincipal());
        var refreshJWT = tokenService.generateRefreshToken((UserEntity) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDto(tokenJWT, refreshJWT));
    }

    @Operation(summary = "Refresh token", description = "Using a refresh token to generate a new access token",
            tags = {"Authentication"},
            responses = {
                    @ApiResponse(responseCode = "200", ref = "#/components/responses/Authentication200Response"),
            })
    @PostMapping("/refresh")
    public ResponseEntity<Object> refreshAccessToken(@RequestBody @Valid TokenRefreshDto tokenDto) {
        var subject = tokenService.getSubject(tokenDto.refresh());
        var user = userRepository.findByUsernameEntity(subject);

        if (user.isPresent()) {
            var newAccessToken = tokenService.generateToken(user.get());
            var newRefreshToken = tokenService.generateRefreshToken(user.get());

            return ResponseEntity.ok(new TokenDto(newAccessToken, newRefreshToken));
        } else {
            return ResponseEntity.badRequest().body("Invalid refresh token");
        }
    }
}
