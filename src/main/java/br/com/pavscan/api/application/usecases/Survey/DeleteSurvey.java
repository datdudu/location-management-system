package br.com.pavscan.api.application.usecases.Survey;

import br.com.pavscan.api.application.gateways.SurveyRepository;
import org.springframework.http.ResponseEntity;

public class DeleteSurvey {
    private final SurveyRepository repository;

    public DeleteSurvey(SurveyRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Object> deleteSurvey(Long id){ return repository.deleteSurvey(id); }
}
