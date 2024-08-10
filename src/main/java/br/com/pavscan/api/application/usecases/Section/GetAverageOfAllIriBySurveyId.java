package br.com.pavscan.api.application.usecases.Section;

import br.com.pavscan.api.application.gateways.SectionRepository;
import br.com.pavscan.api.domain.entities.section.Section;
import org.springframework.http.ResponseEntity;

public class GetAverageOfAllIriBySurveyId {
    private final SectionRepository repository;

    public GetAverageOfAllIriBySurveyId(SectionRepository repository){
        this.repository = repository;
    }
    public Double getAverageOfAllIriBySurveyId(Long surveyId){ return repository.getAverageOfAllIriSurveyId(surveyId); }
}
