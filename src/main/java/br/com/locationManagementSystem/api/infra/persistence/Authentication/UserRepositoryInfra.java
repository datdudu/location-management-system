package br.com.locationManagementSystem.api.infra.persistence.Authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepositoryInfra extends JpaRepository<UserEntity, Long> {
    UserDetails findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<UserEntity> findByUsernameEntity(String username);

}
