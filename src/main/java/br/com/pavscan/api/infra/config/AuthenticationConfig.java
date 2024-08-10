package br.com.pavscan.api.infra.config;

import br.com.pavscan.api.application.gateways.UserRepository;
import br.com.pavscan.api.application.usecases.User.*;
import br.com.pavscan.api.infra.gateways.User.UserEntityMapper;
import br.com.pavscan.api.infra.gateways.User.UserRepositoryJpa;
import br.com.pavscan.api.infra.persistence.Authentication.UserRepositoryInfra;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthenticationConfig {
    @Bean
    CreateUser createUser(UserRepository userRepository){ return new CreateUser(userRepository); }

    @Bean
    LoadUserByUsername loadUserByUsername(UserRepository userRepository){ return new LoadUserByUsername(userRepository);}
    @Bean
    DeleteUser deleteUser(UserRepository userRepository){ return new DeleteUser(userRepository); }

    @Bean
    GetUserByEmail getUserByEmail(UserRepository userRepository){ return new GetUserByEmail(userRepository); }

    @Bean
    GetUserByUsername getUserByUsername(UserRepository userRepository){ return new GetUserByUsername(userRepository); }

    @Bean
    GetUserById getUserById(UserRepository userRepository){ return new GetUserById(userRepository); }

    @Bean
    ListUsers listUsers(UserRepository userRepository){ return new ListUsers(userRepository); }

    @Bean
    UpdateUser updateUser(UserRepository userRepository){return new UpdateUser(userRepository); }

    @Bean
    ResetPassword resetPassword(UserRepository userRepository){ return new ResetPassword(userRepository); }

    @Bean
    UserRepositoryJpa createUserRepositoryJpa(UserRepositoryInfra repository, UserEntityMapper mapper, PasswordEncoder passwordEncoder){
        return new UserRepositoryJpa(repository, mapper, passwordEncoder);
    }
    @Bean
    UserEntityMapper returnUserMapper(){
        return new UserEntityMapper();
    }
}
