package br.com.locationManagementSystem.api.application.usecases.User;

import br.com.locationManagementSystem.api.application.gateways.UserRepository;
import br.com.locationManagementSystem.api.domain.entities.user.User;

import java.util.List;

public class ListUsers {
    private final UserRepository repository;

    public ListUsers(UserRepository repository){
        this.repository = repository;
    }
    public List<User> listUsers(){ return repository.listUsers(); }
}
