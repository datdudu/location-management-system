package br.com.locationManagementSystem.api.domain.entities.location;

public class LocationFactory {
    private Location location;

    public Location withBasicInformations(String name, String neighborhood, String city, String state){
        this.location = new Location(name, neighborhood, city, state);

        return this.location;
    }
}
