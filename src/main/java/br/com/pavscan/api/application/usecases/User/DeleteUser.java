package br.com.pavscan.api.application.usecases.User;

import br.com.pavscan.api.application.gateways.UserRepository;
import org.springframework.http.ResponseEntity;

public class DeleteUser {
    private final UserRepository repository;

    public DeleteUser(UserRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Object> deleteUser(Long id){ return repository.deleteUser(id); }
}
