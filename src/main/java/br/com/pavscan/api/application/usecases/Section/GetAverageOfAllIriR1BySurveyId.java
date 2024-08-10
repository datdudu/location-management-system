package br.com.pavscan.api.application.usecases.Section;

import br.com.pavscan.api.application.gateways.SectionRepository;

public class GetAverageOfAllIriR1BySurveyId {
    private final SectionRepository repository;

    public GetAverageOfAllIriR1BySurveyId(SectionRepository repository){
        this.repository = repository;
    }
    public Double getAverageOfAllIriR1BySurveyId(Long surveyId){ return repository.getAverageOfAllIriR1BySurveyId(surveyId); }
}
