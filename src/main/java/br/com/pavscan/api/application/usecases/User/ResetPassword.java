package br.com.pavscan.api.application.usecases.User;

import br.com.pavscan.api.application.gateways.UserRepository;
import br.com.pavscan.api.domain.entities.user.User;
import org.springframework.http.ResponseEntity;

public class ResetPassword {
    private final UserRepository repository;

    public ResetPassword(UserRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<User> resetPassword(User user){ return repository.resetPassword(user); }
}
