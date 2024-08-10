package br.com.pavscan.api.application.usecases.User;

import br.com.pavscan.api.application.gateways.UserRepository;
import br.com.pavscan.api.domain.entities.user.User;
import org.springframework.http.ResponseEntity;

public class UpdateUser {
    private final UserRepository repository;

    public UpdateUser(UserRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<User> updateUser(Long id, String username, String email, String firstName, String lastName, String newPassword){ return repository.updateUser(id, username, email, firstName, lastName, newPassword); }
}
