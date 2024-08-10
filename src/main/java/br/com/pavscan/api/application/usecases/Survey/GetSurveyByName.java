package br.com.pavscan.api.application.usecases.Survey;

import br.com.pavscan.api.application.gateways.SurveyRepository;
import br.com.pavscan.api.domain.entities.survey.Survey;
import org.springframework.http.ResponseEntity;

public class GetSurveyByName {
    private final SurveyRepository repository;

    public GetSurveyByName(SurveyRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Survey> getSurveyByName(String name) {
        return repository.getSurveyByName(name);
    }
}
