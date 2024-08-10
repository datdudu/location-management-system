package br.com.pavscan.api.infra.controller;

import br.com.pavscan.api.application.usecases.Section.*;
import br.com.pavscan.api.domain.entities.section.Section;
import br.com.pavscan.api.domain.entities.survey.Survey;
import br.com.pavscan.api.infra.controller.Dtos.Section.SectionDto;
import br.com.pavscan.api.infra.persistence.Survey.SurveyRepositoryInfra;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/sections")
@SecurityRequirement(name = "bearer-key")
public class SectionController {
    private final CreateSection createSection;
    private final GetSectionByEmail getSectionByEmail;
    private final UpdateSection updateSection;
    private final DeleteSection deleteSection;
    private final GetAverageOfAllRmsBySurveyId getAverageOfAllRmsBySurveyId;
    private final GetAverageOfAllRmsR1BySurveyId getAverageOfAllRmsR1BySurveyId;
    private final GetAverageOfAllIriBySurveyId getAverageOfAllIriBySurveyId;
    private  final  GetAverageOfAllIriR1BySurveyId getAverageOfAllIriR1BySurveyId;
    private  final GetAverageOfAverageSpeedBySurveyId getAverageOfAverageSpeedBySurveyId;
    private final GetAverageOfMaxSpeedBySurveyId getAverageOfMaxSpeedBySurveyId;
    private final GetAverageOfAllDistancesBySurveyId getAverageOfAllDistancesBySurveyId;
    private final GetFirstBySurveyId getFirstBySurveyId;
    private final GetLastBySurveyId getLastBySurveyId;
    public SectionController(CreateSection createSection, GetSectionByEmail getSectionByEmail, UpdateSection updateSection, DeleteSection deleteSection, GetAverageOfAllRmsBySurveyId getAverageOfAllRmsBySurveyId, GetAverageOfAllRmsR1BySurveyId getAverageOfAllRmsR1BySurveyId, GetAverageOfAllIriBySurveyId getAverageOfAllIriBySurveyId, GetAverageOfAllIriR1BySurveyId getAverageOfAllIriR1BySurveyId, GetAverageOfAverageSpeedBySurveyId getAverageOfAverageSpeedBySurveyId, GetAverageOfMaxSpeedBySurveyId getAverageOfMaxSpeedBySurveyId, GetAverageOfAllDistancesBySurveyId getAverageOfAllDistancesBySurveyId, GetFirstBySurveyId getFirstBySurveyId, GetLastBySurveyId getLastBySurveyId) {
        this.createSection = createSection;
        this.getSectionByEmail = getSectionByEmail;
        this.updateSection = updateSection;
        this.deleteSection = deleteSection;
        this.getAverageOfAllRmsBySurveyId = getAverageOfAllRmsBySurveyId;
        this.getAverageOfAllRmsR1BySurveyId = getAverageOfAllRmsR1BySurveyId;
        this.getAverageOfAllIriBySurveyId = getAverageOfAllIriBySurveyId;
        this.getAverageOfAllIriR1BySurveyId = getAverageOfAllIriR1BySurveyId;
        this.getAverageOfAverageSpeedBySurveyId = getAverageOfAverageSpeedBySurveyId;
        this.getAverageOfMaxSpeedBySurveyId = getAverageOfMaxSpeedBySurveyId;
        this.getAverageOfAllDistancesBySurveyId = getAverageOfAllDistancesBySurveyId;
        this.getFirstBySurveyId = getFirstBySurveyId;
        this.getLastBySurveyId = getLastBySurveyId;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createSection(@RequestBody SectionDto sectionDto){
        ResponseEntity<Section> save = createSection.createSection(SectionDto.toEntity(sectionDto));

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Registering a new section" + localDateTime);

        return save;
    }


    @GetMapping("/averageDistance")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Double> getAverageDistance(Long surveyId) {
        Double averageDistances = getAverageOfAllDistancesBySurveyId.getAverageOfAllDistancesBySurveyId(surveyId);

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Getting a average of all distances based on the surveyId field" + localDateTime);

        return ResponseEntity.ok(averageDistances);
    }


    @GetMapping("/averageRMS")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Double> getAverageRMS(Long surveyId) {
        Double averageOfAllRMS = getAverageOfAllRmsBySurveyId.getAverageOfAllRmsBySurveyId(surveyId);

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Getting a average of all rms based on the surveyId field" + localDateTime);

        return ResponseEntity.ok(averageOfAllRMS);
    }

    @GetMapping("/averageRMSR1")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Double> getAverageRMSR1(Long surveyId) {
        Double averageOfAllRMSR1 = getAverageOfAllRmsR1BySurveyId.getAverageOfAllRmsR1BySurveyId(surveyId);

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Getting a average of all rmsR1 based on the surveyId field" + localDateTime);

        return ResponseEntity.ok(averageOfAllRMSR1);
    }

    @GetMapping("/averageIRI")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Double> getAverageIRI(Long surveyId) {
        Double averageOfAllIRI = getAverageOfAllIriBySurveyId.getAverageOfAllIriBySurveyId(surveyId);

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Getting a average of all iri based on the surveyId field" + localDateTime);

        return ResponseEntity.ok(averageOfAllIRI);
    }

    @GetMapping("/averageIRIR1")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Double> getAverageIRIR1(Long surveyId) {
        Double averageOfAllIRIR1 = getAverageOfAllIriR1BySurveyId.getAverageOfAllIriR1BySurveyId(surveyId);

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Getting a average of all iriR1 based on the surveyId field" + localDateTime);

        return ResponseEntity.ok(averageOfAllIRIR1);
    }


    @GetMapping("/averageAverageSpeed")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Double> getAverageAverageSpeed(Long surveyId) {
        Double averageOfAverageSpeed = getAverageOfAverageSpeedBySurveyId.getAverageOfAverageSpeedBySurveyId(surveyId);

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Getting a average of all averageSpeed based on the surveyId field" + localDateTime);

        return ResponseEntity.ok(averageOfAverageSpeed);
    }

    @GetMapping("/averageMaxSpeed")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Double> getAverageMaxSpeed(Long surveyId) {
        Double averageOfMaxSpeed = getAverageOfMaxSpeedBySurveyId.getAverageOfMaxSpeedBySurveyId(surveyId);

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Getting a average of all maxSpeed based on the surveyId field" + localDateTime);

        return ResponseEntity.ok(averageOfMaxSpeed);
    }

    @GetMapping("/first")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Section> getFirstBySurveyId(Long surveyId) {
        ResponseEntity<Section> firstSection = getFirstBySurveyId.getFirstBySurveyId(surveyId);

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Getting a the first section based on the surveyId field" + localDateTime);

        return firstSection;
    }

    @GetMapping("/last")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Section> getLastBySurveyId(Long surveyId) {
        ResponseEntity<Section> lastSection = getLastBySurveyId.getLastBySurveyId(surveyId);

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Getting a the last section based on the surveyId field" + localDateTime);

        return lastSection;
    }

    @GetMapping("{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Section> getSectionByEmail(@PathVariable ( value = "email") String email) {
        ResponseEntity<Section> sectionRequested = getSectionByEmail.getSectionByEmail(email);
        if(sectionRequested.hasBody()){
            return ResponseEntity.ok(sectionRequested.getBody());
        }

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Getting a section by name " + localDateTime);

        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SectionDto> updateSection(@RequestBody SectionDto sectionDto, @PathVariable(value = "id") Long id){

        ResponseEntity<Section> sectionUpdated = updateSection.updateSection(SectionDto.toEntity(sectionDto), id);

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Updating a message " + localDateTime);

        return ResponseEntity.ok(SectionDto.fromEntity(sectionUpdated.getBody()));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteSection(@PathVariable ( value = "id") Long id) {

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Deleting a message " + localDateTime);

        return deleteSection.deleteSection(id);
    }
}
