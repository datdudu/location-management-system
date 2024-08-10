package br.com.pavscan.api.application.usecases.Section;

import br.com.pavscan.api.application.gateways.SectionRepository;

public class GetAverageOfMaxSpeedBySurveyId {
    private final SectionRepository repository;

    public GetAverageOfMaxSpeedBySurveyId(SectionRepository repository){
        this.repository = repository;
    }
    public Double getAverageOfMaxSpeedBySurveyId(Long surveyId){ return repository.getAverageOfAllMaxSpeedBySurveyId(surveyId); }
}
