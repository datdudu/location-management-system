package br.com.locationManagementSystem.api.application.usecases.Location;

import br.com.locationManagementSystem.api.application.gateways.LocationRepository;
import br.com.locationManagementSystem.api.domain.entities.location.Location;
import org.springframework.http.ResponseEntity;

public class UpdateLocation {
    private final LocationRepository repository;

    public UpdateLocation(LocationRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Location> updateLocation(Location location, Long id){ return repository.updateLocation(location, id); }
}
