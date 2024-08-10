package br.com.pavscan.api.application.usecases.Section;

import br.com.pavscan.api.application.gateways.SectionRepository;

public class GetAverageOfAllDistancesBySurveyId {
    private final SectionRepository repository;

    public GetAverageOfAllDistancesBySurveyId(SectionRepository repository){
        this.repository = repository;
    }
    public Double getAverageOfAllDistancesBySurveyId(Long surveyId){ return repository.getAverageOfAllDistancesBySurveyId(surveyId); }
}

