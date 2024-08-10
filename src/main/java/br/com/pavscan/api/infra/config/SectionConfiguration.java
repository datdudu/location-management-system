package br.com.pavscan.api.infra.config;

import br.com.pavscan.api.application.gateways.SectionRepository;
import br.com.pavscan.api.application.usecases.Section.*;
import br.com.pavscan.api.infra.gateways.Section.SectionEntityMapper;
import br.com.pavscan.api.infra.gateways.Section.SectionRepositoryJpa;
import br.com.pavscan.api.infra.persistence.Section.SectionRepositoryInfra;
import br.com.pavscan.api.infra.persistence.Survey.SurveyRepositoryInfra;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SectionConfiguration {

    @Bean
    CreateSection createSection(SectionRepository repository){ return new CreateSection(repository); }
    @Bean
    GetAverageOfAllDistancesBySurveyId getAverageOfAllDistancesBySurveyId(SectionRepository repository){ return new GetAverageOfAllDistancesBySurveyId(repository); }

    @Bean
    GetAverageOfAllIriBySurveyId getAverageOfAllIriBySurveyId(SectionRepository repository){ return new GetAverageOfAllIriBySurveyId(repository); }

    @Bean
    GetAverageOfAllIriR1BySurveyId getAverageOfAllIriR1BySurveyId(SectionRepository repository){ return new GetAverageOfAllIriR1BySurveyId(repository);}

    @Bean
    GetAverageOfAllRmsBySurveyId getAverageOfAllRmsBySurveyId(SectionRepository repository){ return new GetAverageOfAllRmsBySurveyId(repository);}
    @Bean
    GetAverageOfAllRmsR1BySurveyId getAverageOfAllRmsR1BySurveyId(SectionRepository repository){ return new GetAverageOfAllRmsR1BySurveyId(repository); }
    @Bean
    GetAverageOfAverageSpeedBySurveyId getAverageOfAverageSpeedBySurveyId(SectionRepository repository) { return new GetAverageOfAverageSpeedBySurveyId(repository); }

    @Bean
    GetAverageOfMaxSpeedBySurveyId getAverageOfMaxSpeedBySurveyId(SectionRepository repository){ return new GetAverageOfMaxSpeedBySurveyId(repository); }

    @Bean
    GetFirstBySurveyId getFirstBySurveyId(SectionRepository repository){ return  new GetFirstBySurveyId(repository); }

    @Bean
    GetLastBySurveyId getLastBySurveyId(SectionRepository repository){ return  new GetLastBySurveyId(repository); }
    @Bean
    GetSectionByEmail getSectionByEmail(SectionRepository repository) { return new GetSectionByEmail(repository); }

    @Bean
    UpdateSection updateSection(SectionRepository repository){ return new UpdateSection(repository); }

    @Bean
    DeleteSection deleteSection(SectionRepository repository){ return new DeleteSection(repository); }

    @Bean
    SectionRepositoryJpa createSectionRepositoryJpa(SectionRepositoryInfra repository, SectionEntityMapper mapper, SurveyRepositoryInfra surveyRepositoryInfra){
        return new SectionRepositoryJpa(repository, mapper, surveyRepositoryInfra);
    }

    @Bean
    SectionEntityMapper returnSectionMapper(){
        return new SectionEntityMapper();
    }
}
