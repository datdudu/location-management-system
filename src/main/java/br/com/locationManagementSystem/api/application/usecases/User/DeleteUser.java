package br.com.locationManagementSystem.api.application.usecases.User;

import br.com.locationManagementSystem.api.application.gateways.UserRepository;
import org.springframework.http.ResponseEntity;

public class DeleteUser {
    private final UserRepository repository;

    public DeleteUser(UserRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Object> deleteUser(Long id){ return repository.deleteUser(id); }
}
