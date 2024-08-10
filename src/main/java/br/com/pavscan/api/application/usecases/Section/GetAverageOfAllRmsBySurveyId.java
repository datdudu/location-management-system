package br.com.pavscan.api.application.usecases.Section;

import br.com.pavscan.api.application.gateways.SectionRepository;

public class GetAverageOfAllRmsBySurveyId {
    private final SectionRepository repository;

    public GetAverageOfAllRmsBySurveyId(SectionRepository repository){
        this.repository = repository;
    }
    public Double getAverageOfAllRmsBySurveyId(Long surveyId){ return repository.getAverageOfAllRmsR1BySurveyId(surveyId); }
}
