package br.com.pavscan.api.domain.entities.highway;

import br.com.pavscan.api.domain.entities.survey.Survey;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Highway {
    private Long id;

    private String name;

    private Integer distance;

    private String coatingType;

    private Double latStart;

    private Double latEnd;

    private Double longStart;

    private Double longEnd;

    private List<Survey> surveys;

    public Highway() {
    }

    public Highway(String name, Integer distance, String coatingType, Double latStart, Double latEnd, Double longStart, Double longEnd) {
        this.name = name;
        this.distance = distance;
        this.coatingType = coatingType;
        this.latStart = latStart;
        this.latEnd = latEnd;
        this.longStart = longStart;
        this.longEnd = longEnd;
    }

    public Highway(Long id, String name, Integer distance, String coatingType, Double latStart, Double latEnd, Double longStart, Double longEnd) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.coatingType = coatingType;
        this.latStart = latStart;
        this.latEnd = latEnd;
        this.longStart = longStart;
        this.longEnd = longEnd;
    }
}
