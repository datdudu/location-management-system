package br.com.pavscan.api.infra.controller.Dtos.Section;

import br.com.pavscan.api.domain.entities.section.Section;
import br.com.pavscan.api.infra.controller.Dtos.Survey.SurveyDto;
import jakarta.validation.constraints.NotNull;

public record SectionDto(
        String key,
        Long index,
        String surveyName,
        String vehicleType,
        Double latStart,
        Double latEnd,
        Double longStart,
        Double longEnd,
        Double averageSpeed,
        Double maxSpeed,
        Double dist1,
        Double dist2,
        Double dist3,
        Integer altitude,
        Long dtStart,
        Long dtEnd,
        Double acc,
        Integer size,
        Double rms,
        Double rmsR1,
        Double iri,
        Double iriR1,
        String classification,
        String classificationR1,
        Double covAUD,
        Double covBRL,
        String email,
        String imagePath,
        Long surveyId
) {
    public static SectionDto fromEntity(Section section) {
        return new SectionDto(
                section.getKey(),
                section.getIndex(),
                section.getSurveyName(),
                section.getVehicleType(),
                section.getLatStart(),
                section.getLatEnd(),
                section.getLongStart(),
                section.getLongEnd(),
                section.getAverageSpeed(),
                section.getMaxSpeed(),
                section.getDist1(),
                section.getDist2(),
                section.getDist3(),
                section.getAltitude(),
                section.getDtStart(),
                section.getDtEnd(),
                section.getAcc(),
                section.getSize(),
                section.getRms(),
                section.getRmsR1(),
                section.getIri(),
                section.getIriR1(),
                section.getClassification(),
                section.getClassificationR1(),
                section.getCovAUD(),
                section.getCovBRL(),
                section.getEmail(),
                section.getImagePath(),
                section.getSurveyId()
        );
    }

    public static Section toEntity(SectionDto dto) {
        Section section = new Section();
        section.setKey(dto.key());
        section.setIndex(dto.index());
        section.setSurveyName(dto.surveyName());
        section.setVehicleType(dto.vehicleType());
        section.setLatStart(dto.latStart());
        section.setLatEnd(dto.latEnd());
        section.setLongStart(dto.longStart());
        section.setLongEnd(dto.longEnd());
        section.setAverageSpeed(dto.averageSpeed());
        section.setMaxSpeed(dto.maxSpeed());
        section.setDist1(dto.dist1());
        section.setDist2(dto.dist2());
        section.setDist3(dto.dist3());
        section.setAltitude(dto.altitude());
        section.setDtStart(dto.dtStart());
        section.setDtEnd(dto.dtEnd());
        section.setAcc(dto.acc());
        section.setSize(dto.size());
        section.setRms(dto.rms());
        section.setRmsR1(dto.rmsR1());
        section.setIri(dto.iri());
        section.setIriR1(dto.iriR1());
        section.setClassification(dto.classification());
        section.setClassificationR1(dto.classificationR1());
        section.setCovAUD(dto.covAUD());
        section.setCovBRL(dto.covBRL());
        section.setEmail(dto.email());
        section.setImagePath(dto.imagePath());
        section.setSurveyId(dto.surveyId());
        return section;
    }
}
