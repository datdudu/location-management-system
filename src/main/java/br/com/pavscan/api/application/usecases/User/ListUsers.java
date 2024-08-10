package br.com.pavscan.api.application.usecases.User;

import br.com.pavscan.api.application.gateways.UserRepository;
import br.com.pavscan.api.domain.entities.user.User;

import java.util.List;

public class ListUsers {
    private final UserRepository repository;

    public ListUsers(UserRepository repository){
        this.repository = repository;
    }
    public List<User> listUsers(){ return repository.listUsers(); }
}
