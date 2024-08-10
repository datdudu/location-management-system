package br.com.pavscan.api.application.usecases.Survey;

import br.com.pavscan.api.application.gateways.SurveyRepository;
import br.com.pavscan.api.domain.entities.survey.Survey;
import org.springframework.http.ResponseEntity;

public class CreateSurvey {
    private final SurveyRepository repository;

    public CreateSurvey(SurveyRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Survey> createSurvey(Survey survey){ return repository.createSurvey(survey); }
}
