package br.com.pavscan.api.application.gateways;

import br.com.pavscan.api.domain.entities.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository {
    ResponseEntity<User> getUserByEmail(String email);

    ResponseEntity<User> getUserById(Long Id);

    ResponseEntity<User> getUserByUsername(String username);


    UserDetails loadUserByUsername(String login);

    User createUser(User user);

    ResponseEntity<Object> deleteUser(Long id);

    List<User> listUsers();

    ResponseEntity<User> resetPassword(User user);

    ResponseEntity<User> updateUser(Long id, String username, String email, String firstName, String lastName, String newPassword);
}
