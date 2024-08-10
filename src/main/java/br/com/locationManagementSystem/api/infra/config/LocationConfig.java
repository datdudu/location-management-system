package br.com.locationManagementSystem.api.infra.config;

import br.com.locationManagementSystem.api.application.gateways.LocationRepository;
import br.com.locationManagementSystem.api.application.usecases.Location.*;
import br.com.locationManagementSystem.api.infra.gateways.Location.LocationEntityMapper;
import br.com.locationManagementSystem.api.infra.gateways.Location.LocationRepositoryJpa;
import br.com.locationManagementSystem.api.infra.persistence.Location.LocationRepositoryInfra;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocationConfig {
    @Bean
    GetAllLocations getAllLocations(LocationRepository locationRepository){ return new GetAllLocations(locationRepository); }

    @Bean
    CreateLocation createLocation(LocationRepository locationRepository){
        return new CreateLocation(locationRepository);
    }

    @Bean
    GetLocationByName getLocationByName(LocationRepository locationRepository){
        return new GetLocationByName(locationRepository);
    }

    @Bean
    UpdateLocation updateLocation(LocationRepository locationRepository){
        return new UpdateLocation(locationRepository);
    }

    @Bean
    DeleteLocation deleteLocation(LocationRepository locationRepository){
        return new DeleteLocation(locationRepository);
    }
    @Bean
    LocationRepositoryJpa creteLocationRepositoryJpa(LocationEntityMapper mapper, LocationRepositoryInfra repository){
        return new LocationRepositoryJpa(mapper, repository);
    }

    @Bean
    LocationEntityMapper returnLocationMapper(){
        return new LocationEntityMapper();
    }
}
