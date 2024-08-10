package br.com.pavscan.api.infra.gateways.Section;

import br.com.pavscan.api.domain.entities.section.Section;
import br.com.pavscan.api.infra.gateways.Survey.SurveyEntityMapper;
import br.com.pavscan.api.infra.persistence.Section.SectionEntity;


public class SectionEntityMapper {
    public SectionEntity toEntity(Section section) {
        SectionEntity entity = new SectionEntity(
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
                section.getImagePath()
        );
        entity.setId(section.getId());
        return entity;
    }

    public Section toDomain(SectionEntity entity) {
        Section section = new Section(
                entity.getKey(),
                entity.getIndex(),
                entity.getSurveyName(),
                entity.getVehicleType(),
                entity.getLatStart(),
                entity.getLatEnd(),
                entity.getLongStart(),
                entity.getLongEnd(),
                entity.getAverageSpeed(),
                entity.getMaxSpeed(),
                entity.getDist1(),
                entity.getDist2(),
                entity.getDist3(),
                entity.getAltitude(),
                entity.getDtStart(),
                entity.getDtEnd(),
                entity.getAcc(),
                entity.getSize(),
                entity.getRms(),
                entity.getRmsR1(),
                entity.getIri(),
                entity.getIriR1(),
                entity.getClassification(),
                entity.getClassificationR1(),
                entity.getCovAUD(),
                entity.getCovBRL(),
                entity.getEmail(),
                entity.getImagePath()
        );
        section.setId(entity.getId());
        return section;
    }
}
