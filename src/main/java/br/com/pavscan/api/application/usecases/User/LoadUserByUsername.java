package br.com.pavscan.api.application.usecases.User;

import br.com.pavscan.api.application.gateways.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;

public class LoadUserByUsername {
    private final UserRepository repository;

    public LoadUserByUsername(UserRepository repository){
        this.repository = repository;
    }
    public UserDetails loadUserByUsername(String login){ return repository.loadUserByUsername(login); }
}
