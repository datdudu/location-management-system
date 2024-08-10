package br.com.locationManagementSystem.api.application.usecases.Location;

import br.com.locationManagementSystem.api.application.gateways.LocationRepository;
import br.com.locationManagementSystem.api.domain.entities.location.Location;

public class CreateLocation {
    private final LocationRepository repository;

    public CreateLocation(LocationRepository repository){
        this.repository = repository;
    }
    public Location createLocation(Location location){ return repository.createLocation(location); }
}
