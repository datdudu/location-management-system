package br.com.pavscan.api.infra.gateways.Survey;

import br.com.pavscan.api.domain.entities.survey.Survey;
import br.com.pavscan.api.infra.gateways.Highway.HighwayEntityMapper;
import br.com.pavscan.api.infra.gateways.Section.SectionEntityMapper;
import br.com.pavscan.api.infra.gateways.User.UserEntityMapper;
import br.com.pavscan.api.infra.persistence.Survey.SurveyEntity;

import java.util.stream.Collectors;

public class SurveyEntityMapper {
    public SurveyEntity toEntity(Survey survey) {
        if(survey == null) return null;

        SurveyEntity entity = new SurveyEntity(
                survey.getName(),
                survey.getDatetime(),
                survey.getLatStart(),
                survey.getLatEnd(),
                survey.getLongStart(),
                survey.getLongEnd(),
                survey.getRms(),
                survey.getRmsR1(),
                survey.getIri(),
                survey.getIriR1(),
                survey.getClassification(),
                survey.getClassificationR1(),
                survey.getCovAUD(),
                survey.getCovBRL(),
                survey.getDistance(),
                survey.getVehicleType(),
                survey.getAverageSpeed(),
                survey.getMaxSpeed()
        );

        entity.setId(survey.getId());
        if (survey.getSections() != null) {
            entity.setSections(survey.getSections().stream().map(s -> new SectionEntityMapper().toEntity(s)).collect(Collectors.toList()));
        }

        return entity;
    }

    public Survey toDomain(SurveyEntity entity) {
        Survey survey = new Survey(
                entity.getName(),
                entity.getDatetime(),
                entity.getLatStart(),
                entity.getLongStart(),
                entity.getLatEnd(),
                entity.getLongEnd(),
                entity.getRms(),
                entity.getRmsR1(),
                entity.getIri(),
                entity.getIriR1(),
                entity.getClassification(),
                entity.getClassificationR1(),
                entity.getCovAUD(),
                entity.getCovBRL(),
                entity.getDistance(),
                entity.getVehicleType(),
                entity.getAverageSpeed(),
                entity.getMaxSpeed()
        );
        survey.setId(entity.getId());
        if (entity.getSections() != null) {
            survey.setSections(entity.getSections().stream().map(s -> new SectionEntityMapper().toDomain(s)).collect(Collectors.toList()));
        }

        return survey;
    }
}
