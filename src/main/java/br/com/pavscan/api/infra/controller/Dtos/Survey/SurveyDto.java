package br.com.pavscan.api.infra.controller.Dtos.Survey;

import br.com.pavscan.api.domain.entities.survey.Survey;
import br.com.pavscan.api.infra.controller.Dtos.Highway.HighwayDto;
import br.com.pavscan.api.infra.controller.Dtos.Section.SectionDto;
import br.com.pavscan.api.infra.controller.Dtos.User.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public record SurveyDto(
        Long id,
        String name,
        String datetime,
        Double latStart,
        Double latEnd,
        Double longStart,
        Double longEnd,
        Double rms,
        Double rmsR1,
        Double iri,
        Double iriR1,
        String classification,
        String classificationR1,
        Double covAUD,
        Double covBRL,
        Double distance,
        String vehicleType,
        Double averageSpeed,
        Double maxSpeed,
        Long userId,
        Long highwayId
) {
    public static SurveyDto fromEntity(Survey survey) {
        return new SurveyDto(
                survey.getId(),
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
                survey.getMaxSpeed(),
                survey.getUserId(),
                survey.getHighwayId()
        );
    }

    public static Survey toEntity(SurveyDto dto) {
        Survey survey = new Survey();
        survey.setName(dto.name());
        survey.setDatetime(dto.datetime());
        survey.setLatStart(dto.latStart());
        survey.setLatEnd(dto.latEnd());
        survey.setLongStart(dto.longStart());
        survey.setLongEnd(dto.longEnd());
        survey.setRms(dto.rms());
        survey.setRmsR1(dto.rmsR1());
        survey.setIri(dto.iri());
        survey.setIriR1(dto.iriR1());
        survey.setClassification(dto.classification());
        survey.setClassificationR1(dto.classificationR1());
        survey.setCovAUD(dto.covAUD());
        survey.setCovBRL(dto.covBRL());
        survey.setDistance(dto.distance());
        survey.setVehicleType(dto.vehicleType());
        survey.setAverageSpeed(dto.averageSpeed());
        survey.setMaxSpeed(dto.maxSpeed());
        survey.setUserId(dto.userId());
        survey.setHighwayId(dto.highwayId());
        return survey;
    }
}