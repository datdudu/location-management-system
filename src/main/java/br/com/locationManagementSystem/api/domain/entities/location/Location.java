package br.com.locationManagementSystem.api.domain.entities.location;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Location {
    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String city;

    @Getter
    @Setter
    private String state;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Location(String name, String city, String state) {
        this.name = name;
        this.city = city;
        this.state = state;
    }
}
