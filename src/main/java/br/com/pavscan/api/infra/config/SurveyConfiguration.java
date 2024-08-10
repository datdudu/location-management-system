package br.com.pavscan.api.infra.config;

import br.com.pavscan.api.application.gateways.HighwayRepository;
import br.com.pavscan.api.application.gateways.SurveyRepository;
import br.com.pavscan.api.application.usecases.Survey.*;
import br.com.pavscan.api.infra.gateways.Survey.SurveyEntityMapper;
import br.com.pavscan.api.infra.gateways.Survey.SurveyRepositoryJpa;
import br.com.pavscan.api.infra.persistence.Authentication.UserRepositoryInfra;
import br.com.pavscan.api.infra.persistence.Highway.HighwayRepositoryInfra;
import br.com.pavscan.api.infra.persistence.Survey.SurveyRepositoryInfra;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SurveyConfiguration {

    @Bean
    CreateSurvey createSurvey(SurveyRepository repository){ return new CreateSurvey(repository); }

    @Bean
    UpdateSurvey updateSurvey(SurveyRepository repository){ return new UpdateSurvey(repository); }

    @Bean
    DeleteSurvey deleteSurvey(SurveyRepository repository){ return new DeleteSurvey(repository); }

    @Bean
    GetSurveyById getSurveyById(SurveyRepository repository){ return new GetSurveyById(repository); }

    @Bean
    GetSurveyByName getSurveyByName(SurveyRepository repository){ return  new GetSurveyByName(repository); }
    @Bean
    SurveyRepositoryJpa createSurveyRepositoryJpa(SurveyRepositoryInfra repository, SurveyEntityMapper mapper, UserRepositoryInfra userRepositoryInfra, HighwayRepositoryInfra highwayRepositoryInfra){
        return new SurveyRepositoryJpa(repository, mapper, userRepositoryInfra, highwayRepositoryInfra);
    }

    @Bean
    SurveyEntityMapper returnSurveyMapper(){
        return new SurveyEntityMapper();
    }

}
