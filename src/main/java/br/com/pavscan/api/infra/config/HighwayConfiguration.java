package br.com.pavscan.api.infra.config;

import br.com.pavscan.api.application.gateways.HighwayRepository;
import br.com.pavscan.api.application.usecases.Highway.*;
import br.com.pavscan.api.infra.gateways.Highway.HighwayEntityMapper;
import br.com.pavscan.api.infra.gateways.Highway.HighwayRepositoryJpa;
import br.com.pavscan.api.infra.persistence.Highway.HighwayRepositoryInfra;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HighwayConfiguration {
    @Bean
    CreateHighway createHighway(HighwayRepository repository){ return new CreateHighway(repository); }

    @Bean
    DeleteHighway deleteHighway(HighwayRepository repository){ return new DeleteHighway(repository); }

    @Bean
    UpdateHighway updateHighway(HighwayRepository repository){ return new UpdateHighway(repository); }

    @Bean
    GetHighwayByName getHighwayByName(HighwayRepository repository){ return new GetHighwayByName(repository); }

    @Bean
    GetHighwayById getHighwayById(HighwayRepository repository){ return new GetHighwayById(repository); }

    @Bean
    HighwayRepositoryJpa createHighwayRepositoryJpa(HighwayRepositoryInfra repository, HighwayEntityMapper mapper){
        return new HighwayRepositoryJpa(repository, mapper);
    }

    @Bean
    HighwayEntityMapper returnHighwayMapper(){
        return new HighwayEntityMapper();
    }
}
