package br.com.pavscan.api.application.usecases.Section;

import br.com.pavscan.api.application.gateways.SectionRepository;

public class GetAverageOfAllRmsR1BySurveyId {
    private final SectionRepository repository;

    public GetAverageOfAllRmsR1BySurveyId(SectionRepository repository){
        this.repository = repository;
    }
    public Double getAverageOfAllRmsR1BySurveyId(Long surveyId){ return repository.getAverageOfAllRmsR1BySurveyId(surveyId); }
}
