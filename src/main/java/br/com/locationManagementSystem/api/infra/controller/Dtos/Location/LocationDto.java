package br.com.locationManagementSystem.api.infra.controller.Dtos.Location;

import br.com.locationManagementSystem.api.domain.entities.location.Location;

public record LocationDto(String name,
                          String neighborhood,
                          String city,
                          String state) {

    public static Location dtoToDomain(LocationDto dto){
        return new Location(
                dto.name(),
                dto.neighborhood(),
                dto.city(),
                dto.state()
        );
    }

    public static LocationDto domainToDto(Location location){
        return new LocationDto(
                location.getName(),
                location.getNeighborhood(),
                location.getCity(),
                location.getState()
        );
    }
}
