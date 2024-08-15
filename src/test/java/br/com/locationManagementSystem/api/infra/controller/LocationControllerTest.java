package br.com.locationManagementSystem.api.infra.controller;

import br.com.locationManagementSystem.api.application.usecases.Location.*;
import br.com.locationManagementSystem.api.domain.entities.location.Location;
import br.com.locationManagementSystem.api.infra.controller.Dtos.Location.LocationDto;
import br.com.locationManagementSystem.api.infra.controller.Dtos.Location.LocationWithIdDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LocationControllerTest {

    @MockBean
    private LocationController locationController;
    @MockBean
    private CreateLocation createLocation;
    @MockBean
    private GetAllLocations getAllLocations;
    @MockBean
    private UpdateLocation updateLocation;
    @MockBean
    private GetLocationById getLocationById;
    @MockBean
    private DeleteLocation deleteLocation;


    @BeforeEach
    void setUp() {
        createLocation = mock(CreateLocation.class);
        getAllLocations = mock(GetAllLocations.class);
        updateLocation = mock(UpdateLocation.class);
        getLocationById = mock(GetLocationById.class);
        deleteLocation = mock(DeleteLocation.class);
        locationController = new LocationController(createLocation, getAllLocations, updateLocation, getLocationById, deleteLocation);
    }

    @Test
    void testCreateLocationSuccess() {
        // Given
        LocationDto locationDto = new LocationDto("New Location", "Neighbor","City", "State");
        Location location = new Location("New Location","Neighbor", "City", "State");
        Location savedLocation = new Location(1L, "New Location","Neighbor", "City", "State");

        when(createLocation.createLocation(LocationDto.dtoToDomain(locationDto))).thenReturn(savedLocation);

        // When
        ResponseEntity<LocationWithIdDto> response = locationController.createLocation(locationDto);

        // Then
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(LocationWithIdDto.domainToDto(savedLocation), response.getBody());
    }

    @Test
    void testCreateLocationFailure() {
        // Given
        LocationDto locationDto = new LocationDto("","Neighbor", "City", "State");

        // When
        ResponseEntity<LocationWithIdDto> response = locationController.createLocation(locationDto);

        // Then
        assertEquals(400, response.getStatusCodeValue()); // Bad Request for invalid data
    }

    @Test
    void testGetAllLocationsSuccess() {
        // Given
        Location location = new Location(1L, "Neighboor1", "Location1", "City1", "State1");
        List<Location> locations = List.of(location);
        when(getAllLocations.getAllLocations()).thenReturn(locations);

        // When
        List<LocationWithIdDto> response = locationController.getAllLocations();

        // Then
        assertEquals(1, response.size());
        assertEquals(LocationWithIdDto.domainToDto(location), response.get(0));
    }

    @Test
    void testGetAllLocationsFailure() {
        // Given
        when(getAllLocations.getAllLocations()).thenReturn(Collections.emptyList());

        // When
        List<LocationWithIdDto> response = locationController.getAllLocations();

        // Then
        assertEquals(0, response.size());
    }

    @Test
    void testGetLocationByNameSuccess() {
        // Given
        Long locationId = 1L;
        Location location = new Location(1L, "Location1", "Neighbor", "City1", "State1");
        when(getLocationById.getLocationById(locationId)).thenReturn(ResponseEntity.ok(location));

        // When
        ResponseEntity<LocationWithIdDto> response = locationController.getLocationById(locationId);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(LocationWithIdDto.domainToDto(location), response.getBody());
    }

    @Test
    void testGetLocationByNameFailure() {
        // Given
        Long locationId = 0L;
        when(getLocationById.getLocationById(locationId)).thenReturn(ResponseEntity.notFound().build());

        // When
        ResponseEntity<LocationWithIdDto> response = locationController.getLocationById(locationId);

        // Then
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testUpdateLocationSuccess() {
        // Given
        Long locationId = 1L;
        LocationDto locationDto = new LocationDto("Updated Location", "Neighbor", "City", "State");
        Location updatedLocation = new Location(locationId, "Updated Location", "Neighbor", "City", "State");
        when(updateLocation.updateLocation(LocationDto.dtoToDomain(locationDto), locationId)).thenReturn(ResponseEntity.ok(updatedLocation));

        // When
        ResponseEntity<LocationWithIdDto> response = locationController.updateLocation(locationDto, locationId);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(LocationWithIdDto.domainToDto(updatedLocation), response.getBody());
    }

    @Test
    void testUpdateLocationFailure() {
        // Given
        Long locationId = 1L;
        LocationDto locationDto = new LocationDto("", "Neighbor", "City", "State");

        // When
        ResponseEntity<LocationWithIdDto> response = locationController.updateLocation(locationDto, locationId);

        // Then
        assertEquals(400, response.getStatusCodeValue()); // Bad Request for invalid data
    }

    @Test
    void testDeleteLocationSuccess() {
        // Given
        Long locationId = 1L;
        when(deleteLocation.deleteLocation(locationId)).thenReturn(ResponseEntity.noContent().build());

        // When
        ResponseEntity<Object> response = locationController.deleteLocation(locationId);

        // Then
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testDeleteLocationFailure() {
        // Given
        Long locationId = 1L;
        when(deleteLocation.deleteLocation(locationId)).thenReturn(ResponseEntity.notFound().build());

        // When
        ResponseEntity<Object> response = locationController.deleteLocation(locationId);

        // Then
        assertEquals(404, response.getStatusCodeValue());
    }
}