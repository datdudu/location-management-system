package br.com.locationManagementSystem.api.infra.gateways.Location;

import br.com.locationManagementSystem.api.application.gateways.LocationRepository;
import br.com.locationManagementSystem.api.domain.entities.location.Location;
import br.com.locationManagementSystem.api.infra.persistence.Location.LocationEntity;
import br.com.locationManagementSystem.api.infra.persistence.Location.LocationRepositoryInfra;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LocationRepositoryJpa implements LocationRepository {
    private final LocationEntityMapper mapper;
    private final LocationRepositoryInfra repository;

    public LocationRepositoryJpa(LocationEntityMapper mapper, LocationRepositoryInfra repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public List<Location> getAllLocations() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "createdAt"))
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Location createLocation(Location location) {
        LocationEntity entity = mapper.toEntity(location);
        repository.save(entity);

        return mapper.toDomain(entity);
    }

    @Override
    public ResponseEntity<Location> getLocationById(Long id) {
        Optional<LocationEntity> locationEntity = repository.findById(id);

        return locationEntity.map(entity -> ResponseEntity.ok(mapper.toDomain(entity))).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Override
    public ResponseEntity<Location> updateLocation(Location location, Long id) {
        Optional<LocationEntity> locationEntity = repository.findById(id);

        if(locationEntity.isPresent()){
            locationEntity.get().setName(location.getName());
            locationEntity.get().setNeighborhood(location.getNeighborhood());
            locationEntity.get().setCity(location.getCity());
            locationEntity.get().setState(location.getState());

            repository.save(locationEntity.get());

            return ResponseEntity.ok(mapper.toDomain(locationEntity.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Object> deleteLocation(Long id) {
        Optional<LocationEntity> locationEntity = repository.findById(id);

        if(locationEntity.isPresent()) {
            repository.delete(locationEntity.get());

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
