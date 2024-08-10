package br.com.pavscan.api.application.usecases.Survey;

import br.com.pavscan.api.application.gateways.SurveyRepository;
import br.com.pavscan.api.domain.entities.survey.Survey;
import org.springframework.http.ResponseEntity;

public class UpdateSurvey {
    private final SurveyRepository repository;

    public UpdateSurvey(SurveyRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Survey> updateSurvey(Survey survey, Long id){ return repository.updateSurvey(survey, id); }
}
