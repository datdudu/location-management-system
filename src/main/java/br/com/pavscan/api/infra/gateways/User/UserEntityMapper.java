package br.com.pavscan.api.infra.gateways.User;

import br.com.pavscan.api.domain.entities.survey.Survey;
import br.com.pavscan.api.domain.entities.user.User;
import br.com.pavscan.api.infra.gateways.Section.SectionEntityMapper;
import br.com.pavscan.api.infra.gateways.Survey.SurveyEntityMapper;
import br.com.pavscan.api.infra.persistence.Authentication.UserEntity;
import br.com.pavscan.api.infra.persistence.Survey.SurveyEntity;

import java.util.List;
import java.util.stream.Collectors;

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

        // Mapping survey if user has one
        if (user.getSurvey() != null) {
            userEntity.setSurvey(user.getSurvey().stream().map(s -> new SurveyEntityMapper().toEntity(s)).collect(Collectors.toList()));
        }

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

        // Mapping survey if userEntity has one
        if (userEntity.getSurvey() != null) {
            user.setSurvey(userEntity.getSurvey().stream().map(s -> new SurveyEntityMapper().toDomain(s)).collect(Collectors.toList()));
        }

        return user;
    }
}
