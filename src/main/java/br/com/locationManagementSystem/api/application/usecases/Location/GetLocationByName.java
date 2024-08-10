package br.com.locationManagementSystem.api.application.usecases.Location;

import br.com.locationManagementSystem.api.application.gateways.LocationRepository;
import br.com.locationManagementSystem.api.domain.entities.location.Location;
import org.springframework.http.ResponseEntity;

public class GetLocationByName {
    private final LocationRepository repository;

    public GetLocationByName(LocationRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Location> getLocationByName(String message){ return repository.getLocationByName(message); }
}
