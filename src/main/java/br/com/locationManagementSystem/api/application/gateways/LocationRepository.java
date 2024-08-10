package br.com.locationManagementSystem.api.application.gateways;

import br.com.locationManagementSystem.api.domain.entities.location.Location;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LocationRepository {
    List<Location> getAllLocations();
    Location createLocation(Location location);

    ResponseEntity<Location> getLocationByName(String name);

    ResponseEntity<Location> updateLocation(Location location, Long id);

    ResponseEntity<Object> deleteLocation(Long id);
}
