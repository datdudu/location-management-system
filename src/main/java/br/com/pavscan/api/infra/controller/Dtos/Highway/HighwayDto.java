package br.com.pavscan.api.infra.controller.Dtos.Highway;

import br.com.pavscan.api.domain.entities.highway.Highway;
import br.com.pavscan.api.infra.controller.Dtos.Survey.SurveyDto;

import java.util.List;
import java.util.stream.Collectors;

public record HighwayDto(
        Long id,
        String name,
        Integer distance,
        String coatingType,
        Double latStart,
        Double latEnd,
        Double longStart,
        Double longEnd
) {
    public static HighwayDto fromEntity(Highway highway) {
        return new HighwayDto(
                highway.getId(),
                highway.getName(),
                highway.getDistance(),
                highway.getCoatingType(),
                highway.getLatStart(),
                highway.getLatEnd(),
                highway.getLongStart(),
                highway.getLongEnd()
        );
    }

    public static Highway toEntity(HighwayDto dto) {
        Highway highway = new Highway();
        highway.setId(dto.id());
        highway.setName(dto.name());
        highway.setDistance(dto.distance());
        highway.setCoatingType(dto.coatingType());
        highway.setLatStart(dto.latStart());
        highway.setLatEnd(dto.latEnd());
        highway.setLongStart(dto.longStart());
        highway.setLongEnd(dto.longEnd());
        return highway;
    }
}
