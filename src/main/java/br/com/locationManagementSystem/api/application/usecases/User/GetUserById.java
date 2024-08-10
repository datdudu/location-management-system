package br.com.locationManagementSystem.api.application.usecases.User;

import br.com.locationManagementSystem.api.application.gateways.UserRepository;
import br.com.locationManagementSystem.api.domain.entities.user.User;
import org.springframework.http.ResponseEntity;

public class GetUserById {
    private final UserRepository repository;

    public GetUserById(UserRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<User> getUserById(Long id){ return repository.getUserById(id); }
}
