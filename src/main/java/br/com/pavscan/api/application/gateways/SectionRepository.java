package br.com.pavscan.api.application.gateways;

import br.com.pavscan.api.domain.entities.section.Section;
import org.springframework.http.ResponseEntity;

public interface SectionRepository {
    ResponseEntity<Section> createSection(Section section);

    ResponseEntity<Section> getSectionByEmail(String email);

    ResponseEntity<Section> updateSection(Section section, Long id);

    ResponseEntity<Object> deleteSection(Long id);
    Double getAverageOfSumOfAllRmsBySurveyId(Long surveyId);
    Double getAverageOfAllRmsR1BySurveyId(Long surveyId);
    Double getAverageOfAllAverageSpeedBySurveyId(Long surveyId);
    Double getAverageOfAllMaxSpeedBySurveyId(Long surveyId);
    Double getAverageOfAllIriSurveyId(Long surveyId);
    Double getAverageOfAllIriR1BySurveyId(Long surveyId);
    Double getAverageOfAllDistancesBySurveyId(Long surveyId);
    ResponseEntity<Section> getFirstBySurveyId(Long surveyId);
    ResponseEntity<Section> getLastBySurveyId(Long surveyId);


}
