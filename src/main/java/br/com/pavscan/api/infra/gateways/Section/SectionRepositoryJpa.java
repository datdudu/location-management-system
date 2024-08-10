package br.com.pavscan.api.infra.gateways.Section;

import br.com.pavscan.api.application.gateways.SectionRepository;
import br.com.pavscan.api.domain.entities.section.Section;
import br.com.pavscan.api.infra.persistence.Section.SectionEntity;
import br.com.pavscan.api.infra.persistence.Section.SectionRepositoryInfra;
import br.com.pavscan.api.infra.persistence.Survey.SurveyEntity;
import br.com.pavscan.api.infra.persistence.Survey.SurveyRepositoryInfra;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class SectionRepositoryJpa implements SectionRepository {

    private final SectionRepositoryInfra repository;

    private final SectionEntityMapper mapper;

    private final SurveyRepositoryInfra surveyRepositoryInfra;

    public SectionRepositoryJpa(SectionRepositoryInfra repository, SectionEntityMapper mapper, SurveyRepositoryInfra surveyRepositoryInfra) {
        this.repository = repository;
        this.mapper = mapper;
        this.surveyRepositoryInfra = surveyRepositoryInfra;
    }

    @Override
    public ResponseEntity<Section> createSection(Section section) {
        SectionEntity entity = mapper.toEntity(section);
        Optional<SurveyEntity> surveyEntity = surveyRepositoryInfra.findById(section.getSurveyId());
        if(surveyEntity.isEmpty()) return ResponseEntity.notFound().build();

        entity.setSurvey(surveyEntity.get());

        entity = repository.save(entity);

        return ResponseEntity.ok(mapper.toDomain(entity));
    }

    @Override
    public ResponseEntity<Section> getSectionByEmail(String email) {

        SectionEntity entity = repository.findByEmail(email);

        if (entity != null) {

            return ResponseEntity.ok(mapper.toDomain(entity));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Section> updateSection(Section section, Long id) {

        if (repository.existsById(id)) {
            SectionEntity entity = mapper.toEntity(section);
            entity.setId(id);
            SectionEntity updatedEntity = repository.save(entity);

            return ResponseEntity.ok(mapper.toDomain(updatedEntity));
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Object> deleteSection(Long id) {
        var highway = repository.findById(id);
        repository.deleteById(id);
        return ResponseEntity.ok(highway);
    }

    @Override
    public Double getAverageOfSumOfAllRmsBySurveyId(Long surveyId) {
        return repository.averageOfRmsBySurveyId(surveyId);
    }

    @Override
    public Double getAverageOfAllRmsR1BySurveyId(Long surveyId) {
        return repository.averageOfRmsR1BySurveyId(surveyId);
    }

    @Override
    public Double getAverageOfAllAverageSpeedBySurveyId(Long surveyId) {
        return repository.averageOfAverageSpeedBySurveyId(surveyId);
    }

    @Override
    public Double getAverageOfAllMaxSpeedBySurveyId(Long surveyId) {
        return repository.averageOfMaxSpeedBySurveyId(surveyId);
    }

    @Override
    public Double getAverageOfAllIriSurveyId(Long surveyId) {
        return repository.averageOfIriBySurveyId(surveyId);
    }

    @Override
    public Double getAverageOfAllIriR1BySurveyId(Long surveyId) {
        return repository.averageOfIriR1BySurveyId(surveyId);
    }

    @Override
    public Double getAverageOfAllDistancesBySurveyId(Long surveyId) {
        return repository.averageOfDistanceBySurveyId(surveyId);
    }

    @Override
    public ResponseEntity<Section> getFirstBySurveyId(Long surveyId) {
        return ResponseEntity.ok(mapper.toDomain(repository.findFirstBySurveyId(surveyId)));
    }

    @Override
    public ResponseEntity<Section> getLastBySurveyId(Long surveyId) {
        return ResponseEntity.ok(mapper.toDomain(repository.findLastBySurveyId(surveyId)));
    }
}
