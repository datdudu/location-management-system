package br.com.locationManagementSystem.api.domain.entities.location;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Location {
    @Getter
    @Setter
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String neighborhood;

    @Setter
    @Getter
    private String city;

    @Getter
    @Setter
    private String state;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Location() {
    }

    public Location(String name, String neighborhood, String city, String state) {
        this.name = name;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }

    public Location(Long id, String name, String neighborhood, String city, String state) {
        this.id = id;
        this.neighborhood = neighborhood;
        this.name = name;
        this.city = city;
        this.state = state;
    }
}
