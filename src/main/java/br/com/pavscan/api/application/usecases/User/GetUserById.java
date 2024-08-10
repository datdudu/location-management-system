package br.com.pavscan.api.application.usecases.User;

import br.com.pavscan.api.application.gateways.UserRepository;
import br.com.pavscan.api.domain.entities.user.User;
import org.springframework.http.ResponseEntity;

public class GetUserById {
    private final UserRepository repository;

    public GetUserById(UserRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<User> getUserById(Long id){ return repository.getUserById(id); }
}
