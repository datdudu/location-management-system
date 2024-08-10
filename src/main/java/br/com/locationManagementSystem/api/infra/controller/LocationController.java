package br.com.locationManagementSystem.api.infra.controller;


import br.com.locationManagementSystem.api.application.usecases.Location.*;
import br.com.locationManagementSystem.api.domain.entities.location.Location;
import br.com.locationManagementSystem.api.infra.controller.Dtos.Location.LocationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/locations")
@SecurityRequirement(name = "bearer-key")
public class LocationController {
    private final CreateLocation createLocation;
    private final GetAllLocations getAllLocations;
    private final UpdateLocation updateLocation;
    private final GetLocationByName getLocationByName;
    private final DeleteLocation deleteLocation;

    public LocationController(CreateLocation createLocation, GetAllLocations getAllLocations, UpdateLocation updateLocation, GetLocationByName getLocationByName, DeleteLocation deleteLocation) {
        this.createLocation = createLocation;
        this.getAllLocations = getAllLocations;
        this.updateLocation = updateLocation;
        this.getLocationByName = getLocationByName;
        this.deleteLocation = deleteLocation;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a location", description = "Create a new Location",
            tags = {"Location"},
            responses = {
                    @ApiResponse(responseCode = "201", ref = "#/components/responses/Location201Response")
            })
    public LocationDto createLocation(@RequestBody LocationDto locationDto){
        Location save = createLocation.createLocation(LocationDto.dtoToDomain(locationDto));

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Registering a new location" + localDateTime);

        return  LocationDto.domainToDto(save);
    }

    @Operation(summary = "Get a location by a name", description = "Retrieve a location if it exists by name",
            tags = {"Location"},
            responses = {
                    @ApiResponse(responseCode = "200", ref = "#/components/responses/Location200Response"),
                    @ApiResponse(responseCode = "404", ref = "#/components/responses/404Response")
            })
    @GetMapping("{location}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LocationDto> getLocationByName(@PathVariable ( value = "location") String location) {
        ResponseEntity<Location> locationRequested = getLocationByName.getLocationByName(location);
        if(locationRequested.hasBody()){
            LocationDto locationDto = LocationDto.domainToDto(locationRequested.getBody());
            return ResponseEntity.ok(locationDto);
        }

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Getting a location by name " + localDateTime);

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Updated a location", description = "Giving a Id and a new location, the message location to the id will be updated",
            tags = {"Location"},
            responses = {
                    @ApiResponse(responseCode = "200", ref = "#/components/responses/Location200Response"),
                    @ApiResponse(responseCode = "404", ref = "#/components/responses/404Response")
            })
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LocationDto> updateLocation(@RequestBody LocationDto locationDto, @PathVariable(value = "id") Long id){
        Location locationToUpdate = LocationDto.dtoToDomain(locationDto);

        Location updatedLocation = updateLocation.updateLocation(locationToUpdate, id).getBody();

        LocationDto locationDtoUpdated = LocationDto.domainToDto(updatedLocation);

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Updating a location " + localDateTime);

        return ResponseEntity.ok(locationDtoUpdated);
    }

    @Operation(summary = "Delete a location by a Id", description = "Passing a Id, the location related to that Id will be deleted",
            tags = {"Location"},
            responses = {
                    @ApiResponse(responseCode = "204", ref = "#/components/responses/204Response"),
                    @ApiResponse(responseCode = "404", ref = "#/components/responses/404Response")
            })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteLocation(@PathVariable ( value = "id") Long id) {

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Deleting a message " + localDateTime);

        return deleteLocation.deleteLocation(id);
    }
}
