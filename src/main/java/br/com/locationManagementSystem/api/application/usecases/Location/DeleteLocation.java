package br.com.locationManagementSystem.api.application.usecases.Location;

import br.com.locationManagementSystem.api.application.gateways.LocationRepository;
import br.com.locationManagementSystem.api.domain.entities.location.Location;
import org.springframework.http.ResponseEntity;

public class DeleteLocation {
    private final LocationRepository repository;

    public DeleteLocation(LocationRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Object> deleteLocation(Long id){ return repository.deleteLocation(id); }
}
