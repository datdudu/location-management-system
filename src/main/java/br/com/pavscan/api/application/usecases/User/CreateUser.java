package br.com.pavscan.api.application.usecases.User;

import br.com.pavscan.api.application.gateways.UserRepository;
import br.com.pavscan.api.domain.entities.user.User;

public class CreateUser {
    private final UserRepository repository;

    public CreateUser(UserRepository repository){
        this.repository = repository;
    }
    public User createUser(User user){ return repository.createUser(user); }
}
