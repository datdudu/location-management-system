package br.com.locationManagementSystem.api.application.usecases.User;

import br.com.locationManagementSystem.api.application.gateways.UserRepository;
import br.com.locationManagementSystem.api.domain.entities.user.User;
import org.springframework.http.ResponseEntity;

public class ResetPassword {
    private final UserRepository repository;

    public ResetPassword(UserRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<User> resetPassword(User user){ return repository.resetPassword(user); }
}
