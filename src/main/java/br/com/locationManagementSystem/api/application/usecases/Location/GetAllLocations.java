package br.com.locationManagementSystem.api.application.usecases.Location;

import br.com.locationManagementSystem.api.application.gateways.LocationRepository;
import br.com.locationManagementSystem.api.domain.entities.location.Location;

import java.util.List;

public class GetAllLocations {
    private final LocationRepository repository;

    public GetAllLocations(LocationRepository repository){
        this.repository = repository;
    }
    public List<Location> getAllLocations(){ return repository.getAllLocations(); }
}
