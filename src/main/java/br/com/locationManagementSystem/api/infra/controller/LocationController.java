package br.com.locationManagementSystem.api.infra.controller;


import br.com.locationManagementSystem.api.application.usecases.Location.*;
import br.com.locationManagementSystem.api.domain.entities.location.Location;
import br.com.locationManagementSystem.api.infra.controller.Dtos.Location.LocationDto;
import br.com.locationManagementSystem.api.infra.controller.Dtos.Location.LocationWithIdDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/locations")
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
    public ResponseEntity<LocationWithIdDto> createLocation(@RequestBody LocationDto locationDto){
        if(locationDto.name().isBlank()) return ResponseEntity.badRequest().build();

        Location save = createLocation.createLocation(LocationDto.dtoToDomain(locationDto));
        return  ResponseEntity.ok(LocationWithIdDto.domainToDto(save));
    }

    @Operation(summary = "Get all the locations", description = "Returns a List with all the locations registered",
            tags = {"Location"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LocationWithIdDto.class)))),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping("/getAllLocations")
    @ResponseStatus(HttpStatus.OK)
    public List<LocationWithIdDto> getAllLocations() {
        List<Location> locationsRequested = getAllLocations.getAllLocations();

        return locationsRequested
                .stream()
                .map(location -> LocationWithIdDto.domainToDto(location))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get a location by a name", description = "Retrieve a location if it exists by name",
            tags = {"Location"},
            responses = {
                    @ApiResponse(responseCode = "200", ref = "#/components/responses/Location200Response"),
                    @ApiResponse(responseCode = "404", ref = "#/components/responses/404Response")
            })
    @GetMapping("{location}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LocationWithIdDto> getLocationByName(@PathVariable ( value = "location") String location) {
        ResponseEntity<Location> locationRequested = getLocationByName.getLocationByName(location);
        if(locationRequested.hasBody()){
            LocationWithIdDto locationWithIdDto = LocationWithIdDto.domainToDto(locationRequested.getBody());
            return ResponseEntity.ok(locationWithIdDto);
        }

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
    public ResponseEntity<LocationWithIdDto> updateLocation(@RequestBody LocationDto locationDto, @PathVariable(value = "id") Long id){
        if(locationDto.name().isBlank()) return ResponseEntity.badRequest().build();

        Location locationToUpdate = LocationDto.dtoToDomain(locationDto);

        Location updatedLocation = updateLocation.updateLocation(locationToUpdate, id).getBody();

        LocationWithIdDto locationWithIdDtoUpdated = LocationWithIdDto.domainToDto(updatedLocation);

        return ResponseEntity.ok(locationWithIdDtoUpdated);
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
        return deleteLocation.deleteLocation(id);
    }
}
