package br.com.pavscan.api.infra.gateways.Highway;

import br.com.pavscan.api.domain.entities.highway.Highway;
import br.com.pavscan.api.infra.gateways.Survey.SurveyEntityMapper;
import br.com.pavscan.api.infra.persistence.Highway.HighwayEntity;

import java.util.stream.Collectors;

public class HighwayEntityMapper {
    public HighwayEntity toEntity(Highway highway) {
        HighwayEntity entity = new HighwayEntity(
                highway.getName(),
                highway.getDistance(),
                highway.getCoatingType(),
                highway.getLatStart(),
                highway.getLongStart(),
                highway.getLatEnd(),
                highway.getLongEnd()
        );

        if (highway.getSurveys() != null) {
            entity.setSurveys(highway.getSurveys().stream().map(s -> new SurveyEntityMapper().toEntity(s)).collect(Collectors.toList()));
        }
        return entity;
    }

    public Highway toDomain(HighwayEntity entity) {
        Highway highway = new Highway(
                entity.getId(),
                entity.getName(),
                entity.getDistance(),
                entity.getCoatingType(),
                entity.getLatStart(),
                entity.getLongStart(),
                entity.getLatEnd(),
                entity.getLongEnd()
        );

        if (entity.getSurveys() != null) {
            highway.setSurveys(entity.getSurveys().stream().map(s -> new SurveyEntityMapper().toDomain(s)).collect(Collectors.toList()));
        }

        return highway;
    }
}
