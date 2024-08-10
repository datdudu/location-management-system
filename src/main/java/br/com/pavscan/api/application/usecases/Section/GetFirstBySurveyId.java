package br.com.pavscan.api.application.usecases.Section;

import br.com.pavscan.api.application.gateways.SectionRepository;
import br.com.pavscan.api.domain.entities.section.Section;
import org.springframework.http.ResponseEntity;

public class GetFirstBySurveyId {
    private final SectionRepository repository;

    public GetFirstBySurveyId(SectionRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Section> getFirstBySurveyId(Long surveyId){ return repository.getFirstBySurveyId(surveyId); }
}
