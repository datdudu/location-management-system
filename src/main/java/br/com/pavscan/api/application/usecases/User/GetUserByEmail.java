package br.com.pavscan.api.application.usecases.User;

import br.com.pavscan.api.application.gateways.UserRepository;
import br.com.pavscan.api.domain.entities.user.User;
import org.springframework.http.ResponseEntity;

public class GetUserByEmail {

    private final UserRepository repository;

    public GetUserByEmail(UserRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<User> getUserByEmail(String email){ return repository.getUserByEmail(email); }
}
