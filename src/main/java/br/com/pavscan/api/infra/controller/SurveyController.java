package br.com.pavscan.api.infra.controller;

import br.com.pavscan.api.application.usecases.Survey.*;
import br.com.pavscan.api.domain.entities.highway.Highway;
import br.com.pavscan.api.domain.entities.survey.Survey;
import br.com.pavscan.api.domain.entities.user.User;
import br.com.pavscan.api.infra.controller.Dtos.Section.SectionDto;
import br.com.pavscan.api.infra.controller.Dtos.Survey.SurveyDto;
import br.com.pavscan.api.infra.controller.Dtos.User.UserDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/surveys")
@SecurityRequirement(name = "bearer-key")
public class SurveyController {
    private final CreateSurvey createSurvey;
    private final UpdateSurvey updateSurvey;
    private final DeleteSurvey deleteSurvey;
    private final GetSurveyById getSurveyById;
    private final GetSurveyByName getSurveyByName;

    public SurveyController(CreateSurvey createSurvey, UpdateSurvey updateSurvey, DeleteSurvey deleteSurvey, GetSurveyById getSurveyById, GetSurveyByName getSurveyByName) {
        this.createSurvey = createSurvey;
        this.updateSurvey = updateSurvey;
        this.deleteSurvey = deleteSurvey;
        this.getSurveyById = getSurveyById;
        this.getSurveyByName = getSurveyByName;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Survey> getSurveyById(Long id){
        ResponseEntity<Survey> survey = getSurveyById.getSurveyById(id);

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Get a survey by id" + localDateTime);

        return survey;
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Survey> getSurveyByName(@PathVariable ( value = "name") String name) {
        ResponseEntity<Survey> surveyRequested = getSurveyByName.getSurveyByName(name);
        if(surveyRequested.hasBody()){
            return ResponseEntity.ok(surveyRequested.getBody());
        }

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Getting a survey by name " + localDateTime);

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SurveyDto createSurvey(@RequestBody SurveyDto surveyDto){
        ResponseEntity<Survey> save = createSurvey.createSurvey(SurveyDto.toEntity(surveyDto));

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Registering a new survey" + localDateTime);

        return SurveyDto.fromEntity(save.getBody());
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SurveyDto> updateSurvey(@RequestBody SurveyDto surveyDto, @PathVariable(value = "id") Long id){
        ResponseEntity<Survey> surveyUpdated = updateSurvey.updateSurvey(SurveyDto.toEntity(surveyDto), id);

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Updating a message " + localDateTime);

        return ResponseEntity.ok(SurveyDto.fromEntity(surveyUpdated.getBody()));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteSurvey(@PathVariable ( value = "id") Long id) {

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Deleting a message " + localDateTime);

        return deleteSurvey.deleteSurvey(id);
    }
}
