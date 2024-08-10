package br.com.pavscan.api.infra.gateways.Survey;

import br.com.pavscan.api.application.gateways.SurveyRepository;
import br.com.pavscan.api.domain.entities.highway.Highway;
import br.com.pavscan.api.domain.entities.survey.Survey;
import br.com.pavscan.api.infra.persistence.Authentication.UserEntity;
import br.com.pavscan.api.infra.persistence.Authentication.UserRepositoryInfra;
import br.com.pavscan.api.infra.persistence.Highway.HighwayEntity;
import br.com.pavscan.api.infra.persistence.Highway.HighwayRepositoryInfra;
import br.com.pavscan.api.infra.persistence.Survey.SurveyEntity;
import br.com.pavscan.api.infra.persistence.Survey.SurveyRepositoryInfra;
import jakarta.persistence.Entity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class SurveyRepositoryJpa implements SurveyRepository {
    private final SurveyRepositoryInfra repository;

    private final SurveyEntityMapper mapper;
    private final UserRepositoryInfra userRepositoryInfra;
    private final HighwayRepositoryInfra highwayRepositoryInfra;

    public SurveyRepositoryJpa(SurveyRepositoryInfra repository, SurveyEntityMapper mapper, UserRepositoryInfra userRepositoryInfra, HighwayRepositoryInfra highwayRepositoryInfra) {
        this.repository = repository;
        this.mapper = mapper;
        this.userRepositoryInfra = userRepositoryInfra;
        this.highwayRepositoryInfra = highwayRepositoryInfra;
    }

    @Override
    public ResponseEntity<Survey> getSurveyById(Long id) {
        Optional<SurveyEntity> entity = repository.findById(id);

        return entity.map(surveyEntity -> ResponseEntity.ok(mapper.toDomain(surveyEntity))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Survey> getSurveyByName(String name) {
        SurveyEntity entity = repository.findByName(name);
        if (entity != null) {
            return ResponseEntity.ok(mapper.toDomain(entity));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Survey> createSurvey(Survey survey) {
        SurveyEntity entity = mapper.toEntity(survey);

        Optional<UserEntity> userEntity = userRepositoryInfra.findById(survey.getUserId());
        if(userEntity.isEmpty()) return ResponseEntity.notFound().build();

        Optional<HighwayEntity> highwayEntity = highwayRepositoryInfra.findById(survey.getHighwayId());
        if(highwayEntity.isEmpty()) return ResponseEntity.notFound().build();

        entity.setUser(userEntity.get());
        entity.setHighway(highwayEntity.get());
        entity = repository.save(entity);

        return ResponseEntity.ok(mapper.toDomain(entity));
    }

    @Override
    public ResponseEntity<Survey> updateSurvey(Survey survey, Long id) {
        if (repository.existsById(id)) {
            SurveyEntity entity = mapper.toEntity(survey);
            entity.setId(id);
            SurveyEntity updatedEntity = repository.save(entity);

            return ResponseEntity.ok(mapper.toDomain(updatedEntity));
        }

        return ResponseEntity.notFound().build();

    }

    @Override
    public ResponseEntity<Object> deleteSurvey(Long id) {
        var survey = repository.findById(id);
        repository.deleteById(id);
        return ResponseEntity.ok(survey);
    }
}
