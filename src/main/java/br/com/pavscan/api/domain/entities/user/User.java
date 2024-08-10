package br.com.pavscan.api.domain.entities.user;

import br.com.pavscan.api.domain.entities.survey.Survey;
import br.com.pavscan.api.infra.persistence.Survey.SurveyEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {
    private Long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private List<Survey> survey;

    public User() {
    }

    public User(String username, String email, String firstName, String lastName, String password) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User(Long id, String username, String email, String firstName, String lastName, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User(String email, String firstName) {
        this.email = email;
        this.firstName = firstName;
    }

    public User(Long id, String password) {
        this.id = id;
        this.password = password;
    }
}
