package br.com.pavscan.api.infra.controller;

import br.com.pavscan.api.application.usecases.Highway.*;
import br.com.pavscan.api.application.usecases.Survey.CreateSurvey;
import br.com.pavscan.api.domain.entities.highway.Highway;
import br.com.pavscan.api.domain.entities.survey.Survey;
import br.com.pavscan.api.infra.controller.Dtos.Highway.HighwayDto;
import br.com.pavscan.api.infra.controller.Dtos.Survey.SurveyDto;
import br.com.pavscan.api.infra.gateways.Survey.SurveyEntityMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/highways")
@SecurityRequirement(name = "bearer-key")
public class HighwayController {
    private final CreateHighway createHighway;
    private final GetHighwayByName getHighwayByName;
    private final UpdateHighway updateHighway;
    private final DeleteHighway deleteHighway;
    private final GetHighwayById getHighwayById;

    public HighwayController(CreateHighway createHighway, GetHighwayByName getHighwayByName, UpdateHighway updateHighway, DeleteHighway deleteHighway, GetHighwayById getHighwayById) {
        this.createHighway = createHighway;
        this.getHighwayByName = getHighwayByName;
        this.updateHighway = updateHighway;
        this.deleteHighway = deleteHighway;
        this.getHighwayById = getHighwayById;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HighwayDto createHighway(@RequestBody HighwayDto highwayDto){

        Highway save = createHighway.createHighway(HighwayDto.toEntity(highwayDto));

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Registering a new highway" + localDateTime);

        return HighwayDto.fromEntity(save);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Highway> getHighwayById(Long id){
        ResponseEntity<Highway> highway = getHighwayById.getHighwayById(id);

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Get a highway by id" + localDateTime);

        return highway;
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HighwayDto> getHighwayByName(@PathVariable ( value = "name") String name) {
        ResponseEntity<Highway> highwayRequested = getHighwayByName.getHighwayByName(name);
        if(highwayRequested.hasBody()){
            return ResponseEntity.ok(HighwayDto.fromEntity(highwayRequested.getBody()));
        }

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Getting a highway by name " + localDateTime);

        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HighwayDto> updateHighway(@RequestBody HighwayDto highwayDto, @PathVariable(value = "id") Long id){

        ResponseEntity<Highway> highwayUpdated = updateHighway.updateHighway(HighwayDto.toEntity(highwayDto), id);

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Updating a message " + localDateTime);

        return ResponseEntity.ok(HighwayDto.fromEntity(highwayUpdated.getBody()));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteHighway(@PathVariable ( value = "id") Long id) {

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Deleting a message " + localDateTime);

        return deleteHighway.deleteHighway(id);
    }
}
