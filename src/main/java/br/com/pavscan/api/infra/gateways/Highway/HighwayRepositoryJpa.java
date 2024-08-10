package br.com.pavscan.api.infra.gateways.Highway;

import br.com.pavscan.api.application.gateways.HighwayRepository;
import br.com.pavscan.api.application.gateways.SurveyRepository;
import br.com.pavscan.api.domain.entities.highway.Highway;
import br.com.pavscan.api.domain.entities.survey.Survey;
import br.com.pavscan.api.infra.gateways.Survey.SurveyRepositoryJpa;
import br.com.pavscan.api.infra.persistence.Highway.HighwayEntity;
import br.com.pavscan.api.infra.persistence.Highway.HighwayRepositoryInfra;
import br.com.pavscan.api.infra.persistence.Survey.SurveyEntity;
import br.com.pavscan.api.infra.persistence.Survey.SurveyRepositoryInfra;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class HighwayRepositoryJpa implements HighwayRepository {
    private final HighwayRepositoryInfra repositoryInfra;

    private final HighwayEntityMapper mapper;

    public HighwayRepositoryJpa(HighwayRepositoryInfra repositoryInfra, HighwayEntityMapper mapper) {
        this.repositoryInfra = repositoryInfra;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Highway> getHighwayById(Long id) {
        Optional<HighwayEntity> entity = repositoryInfra.findById(id);

        return entity.map(highwayEntity -> ResponseEntity.ok(mapper.toDomain(highwayEntity))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public Highway createHighway(Highway highway) {
        HighwayEntity entity = mapper.toEntity(highway);
        entity = repositoryInfra.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public ResponseEntity<Highway> getHighwayByName(String name) {

        HighwayEntity entity = repositoryInfra.findByName(name);
        if (entity != null) {

            return ResponseEntity.ok(mapper.toDomain(entity));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Highway> updateHighway(Highway highway, Long id) {

        if (repositoryInfra.existsById(id)) {
            HighwayEntity entity = mapper.toEntity(highway);
            entity.setId(id);
            HighwayEntity updatedEntity = repositoryInfra.save(entity);
            return ResponseEntity.ok(mapper.toDomain(updatedEntity));
        }

        return ResponseEntity.notFound().build();

    }

    @Override
    public ResponseEntity<Object> deleteHighway(Long id) {
        var highway = repositoryInfra.findById(id);
        repositoryInfra.deleteById(id);
        return ResponseEntity.ok(highway);
    }
}
