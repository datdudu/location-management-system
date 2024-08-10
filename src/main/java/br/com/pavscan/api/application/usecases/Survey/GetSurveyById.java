package br.com.pavscan.api.application.usecases.Survey;

import br.com.pavscan.api.application.gateways.SurveyRepository;
import br.com.pavscan.api.domain.entities.survey.Survey;
import org.springframework.http.ResponseEntity;

public class GetSurveyById {
    private final SurveyRepository repository;

    public GetSurveyById(SurveyRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Survey> getSurveyById(Long id){ return repository.getSurveyById(id); }
}
