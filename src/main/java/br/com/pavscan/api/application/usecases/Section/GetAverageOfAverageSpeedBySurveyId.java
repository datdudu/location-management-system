package br.com.pavscan.api.application.usecases.Section;

import br.com.pavscan.api.application.gateways.SectionRepository;

public class GetAverageOfAverageSpeedBySurveyId {
    private final SectionRepository repository;

    public GetAverageOfAverageSpeedBySurveyId(SectionRepository repository){
        this.repository = repository;
    }
    public Double getAverageOfAverageSpeedBySurveyId(Long surveyId){ return repository.getAverageOfAllAverageSpeedBySurveyId(surveyId); }

}
