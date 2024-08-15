package br.com.locationManagementSystem.api.infra.gateways.Location;

import br.com.locationManagementSystem.api.domain.entities.location.Location;
import br.com.locationManagementSystem.api.infra.persistence.Location.LocationEntity;

public class LocationEntityMapper {
    public LocationEntity toEntity(Location location){
        return new LocationEntity(
                location.getName(),
                location.getNeighborhood(),
                location.getCity(),
                location.getState()
        );
    }

    public Location toDomain(LocationEntity entity){
        return new Location(
                entity.getId(),
                entity.getName(),
                entity.getNeighborhood(),
                entity.getCity(),
                entity.getState()
        );
    }
}
