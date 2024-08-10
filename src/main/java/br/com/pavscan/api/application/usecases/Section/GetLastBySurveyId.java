package br.com.pavscan.api.application.usecases.Section;

import br.com.pavscan.api.application.gateways.SectionRepository;
import br.com.pavscan.api.domain.entities.section.Section;
import org.springframework.http.ResponseEntity;

public class GetLastBySurveyId {
    private final SectionRepository repository;

    public GetLastBySurveyId(SectionRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Section> getLastBySurveyId(Long surveyId){ return repository.getLastBySurveyId(surveyId); }
}
