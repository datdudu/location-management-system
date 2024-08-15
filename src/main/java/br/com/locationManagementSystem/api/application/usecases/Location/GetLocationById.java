package br.com.locationManagementSystem.api.application.usecases.Location;

import br.com.locationManagementSystem.api.application.gateways.LocationRepository;
import br.com.locationManagementSystem.api.domain.entities.location.Location;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GetLocationById {
    private final LocationRepository repository;

    public GetLocationById(LocationRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Location> getLocationById(Long id){ return repository.getLocationById(id); }
}
