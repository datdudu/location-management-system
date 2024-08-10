package br.com.locationManagementSystem.api.infra.gateways.User;

import br.com.locationManagementSystem.api.domain.entities.user.User;
import br.com.locationManagementSystem.api.infra.persistence.Authentication.UserEntity;

public class UserEntityMapper {
    public UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity(
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword()
        );


        return userEntity;
    }

    public User toDomain(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        User user = new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getPassword()
        );

        return user;
    }
}
