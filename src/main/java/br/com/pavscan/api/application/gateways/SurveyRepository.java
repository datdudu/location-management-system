package br.com.pavscan.api.application.gateways;

import br.com.pavscan.api.domain.entities.survey.Survey;
import org.springframework.http.ResponseEntity;

public interface SurveyRepository {
    ResponseEntity<Survey> getSurveyById(Long id);

    ResponseEntity<Survey> getSurveyByName(String name);

    ResponseEntity<Survey> createSurvey(Survey survey);

    ResponseEntity<Survey> updateSurvey(Survey survey, Long id);

    ResponseEntity<Object> deleteSurvey(Long id);
}
