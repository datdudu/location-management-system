package br.com.locationManagementSystem.api.infra.gateways.User;

import br.com.locationManagementSystem.api.application.gateways.UserRepository;
import br.com.locationManagementSystem.api.domain.entities.user.User;
import br.com.locationManagementSystem.api.infra.persistence.Authentication.UserEntity;
import br.com.locationManagementSystem.api.infra.persistence.Authentication.UserRepositoryInfra;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepositoryJpa implements UserRepository, UserDetailsService {
    private final UserRepositoryInfra repository;
    private final UserEntityMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserRepositoryJpa(UserRepositoryInfra repository, UserEntityMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        User userToSave = new User(user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName(), encodedPassword);

        UserEntity entity = mapper.toEntity(userToSave);
        repository.save(entity);

        return mapper.toDomain(entity);
    }

    @Override
    public ResponseEntity<Object> deleteUser(Long id) {
        Optional<UserEntity> userEntity = repository.findById(id);

        if(userEntity.isPresent()) {
            repository.delete(userEntity.get());

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    @Override
    public ResponseEntity<User> getUserByEmail(String email) {
        Optional<UserEntity> userEntity = repository.findByEmail(email);

        return userEntity.map(entity -> ResponseEntity.ok(mapper.toDomain(entity))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<User> getUserById(Long id) {
        Optional<UserEntity> userEntity = repository.findById(id);

        return userEntity.map(entity -> ResponseEntity.ok(mapper.toDomain(entity))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<User> getUserByUsername(String username) {
        Optional<UserEntity> userEntity = repository.findByUsernameEntity(username);

        return userEntity.map(entity -> ResponseEntity.ok(mapper.toDomain(entity))).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Override
    public List<User> listUsers() {
        return repository
                .findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<User> resetPassword(User user) {
        Optional<UserEntity> userEntity = repository.findById(user.getId());

        if(userEntity.isPresent()){
            userEntity.get().setPassword(passwordEncoder.encode(user.getPassword()));

            repository.save(userEntity.get());

            return ResponseEntity.ok(mapper.toDomain(userEntity.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<User> updateUser(Long id, String username, String email, String firstName, String lastName, String newPassword) {
        Optional<UserEntity> userEntity = repository.findById(id);

        if(userEntity.isPresent()){
            UserEntity userToBeUpdated = userEntity.get();

            userToBeUpdated.setUsername(username);
            userToBeUpdated.setEmail(email);
            userToBeUpdated.setFirstName(firstName);
            userToBeUpdated.setLastName(lastName);

            if (newPassword != null && !newPassword.isEmpty()) {
                userToBeUpdated.setPassword(passwordEncoder.encode(newPassword));
            }

            repository.save(userToBeUpdated);

            return ResponseEntity.ok(mapper.toDomain(userToBeUpdated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
