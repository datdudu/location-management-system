package br.com.pavscan.api.infra.security;

import br.com.pavscan.api.infra.persistence.Authentication.UserRepositoryInfra;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepositoryInfra repository;

    public SecurityFilter(TokenService tokenService, UserRepositoryInfra repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            var tokenJWT = recoverToken(request);
            if (tokenJWT != null) {
                TokenContext.setToken(tokenJWT);
                var subject = tokenService.getSubject(tokenJWT);
                var user = repository.findByUsername(subject);
                if (user != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);
        } catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"message\": \"Token JWT inválido ou expirado!\", \"refreshToken\": \"" + generateRefreshToken() + "\"}");
            response.getWriter().flush();
        } finally {
            TokenContext.clear();
        }
    }

    private String recoverToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private String generateRefreshToken() {
        var currentToken = TokenContext.getToken();
        if (currentToken != null) {
            var subject = tokenService.getSubject(currentToken);
            var user = repository.findByUsernameEntity(subject);
            if (user.isPresent()) {
                return tokenService.generateRefreshToken(user.get());
            }
        }
        return "";
    }
}
