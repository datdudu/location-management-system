package br.com.locationManagementSystem.api.infra.controller.Dtos.Location;

import br.com.locationManagementSystem.api.domain.entities.location.Location;

public record LocationWithIdDto(
        Long id,
        String name,
        String city,
        String state) {

    public static LocationWithIdDto domainToDto(Location location){
        return new LocationWithIdDto(
                location.getId(),
                location.getName(),
                location.getCity(),
                location.getState()
        );
    }
}
