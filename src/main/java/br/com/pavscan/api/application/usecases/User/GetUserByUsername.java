package br.com.pavscan.api.application.usecases.User;

import br.com.pavscan.api.application.gateways.UserRepository;
import br.com.pavscan.api.domain.entities.user.User;
import org.springframework.http.ResponseEntity;

public class GetUserByUsername {
    private final UserRepository repository;

    public GetUserByUsername(UserRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<User> getUserByUsername(String username){ return repository.getUserByUsername(username); }
}
