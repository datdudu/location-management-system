package br.com.pavscan.api.domain.entities.survey;


import br.com.pavscan.api.domain.entities.highway.Highway;
import br.com.pavscan.api.domain.entities.section.Section;
import br.com.pavscan.api.domain.entities.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Survey {
    private Long id;
    private String name;
    private String datetime;
    private Double latStart;
    private Double latEnd;
    private Double longStart;
    private Double longEnd;
    private Double rms;
    private Double rmsR1;
    private Double iri;
    private Double iriR1;
    private String classification;
    private String classificationR1;
    private Double covAUD;
    private Double covBRL;
    private Double distance;
    private String vehicleType;
    private Double averageSpeed;
    private Double maxSpeed;
    private Long highwayId;
    private Long userId;
    private List<Section> sections;

    public Survey(String name, String datetime, Double latStart, Double latEnd, Double longStart, Double longEnd, Double rms, Double rmsR1, Double iri, Double iriR1, String classification, String classificationR1, Double covAUD, Double covBRL, Double distance, String vehicleType, Double averageSpeed, Double maxSpeed, Long highwayId, Long userId) {
        this.name = name;
        this.datetime = datetime;
        this.latStart = latStart;
        this.latEnd = latEnd;
        this.longStart = longStart;
        this.longEnd = longEnd;
        this.rms = rms;
        this.rmsR1 = rmsR1;
        this.iri = iri;
        this.iriR1 = iriR1;
        this.classification = classification;
        this.classificationR1 = classificationR1;
        this.covAUD = covAUD;
        this.covBRL = covBRL;
        this.distance = distance;
        this.vehicleType = vehicleType;
        this.averageSpeed = averageSpeed;
        this.maxSpeed = maxSpeed;
        this.highwayId = highwayId;
        this.userId = userId;
    }

    public Survey(Long id, String name, String datetime, Double latStart, Double latEnd, Double longStart, Double longEnd, Double rms, Double rmsR1, Double iri, Double iriR1, String classification, String classificationR1, Double covAUD, Double covBRL, Double distance, String vehicleType, Double averageSpeed, Double maxSpeed, Long highwayId, Long userId) {
        this.id = id;
        this.name = name;
        this.datetime = datetime;
        this.latStart = latStart;
        this.latEnd = latEnd;
        this.longStart = longStart;
        this.longEnd = longEnd;
        this.rms = rms;
        this.rmsR1 = rmsR1;
        this.iri = iri;
        this.iriR1 = iriR1;
        this.classification = classification;
        this.classificationR1 = classificationR1;
        this.covAUD = covAUD;
        this.covBRL = covBRL;
        this.distance = distance;
        this.vehicleType = vehicleType;
        this.averageSpeed = averageSpeed;
        this.maxSpeed = maxSpeed;
        this.highwayId = highwayId;
        this.userId = userId;
    }

    public Survey(Long id, String name, String datetime, Double latStart, Double latEnd, Double longStart, Double longEnd, Double rms, Double rmsR1, Double iri, Double iriR1, String classification, String classificationR1, Double covAUD, Double covBRL, Double distance, String vehicleType, Double averageSpeed, Double maxSpeed) {
        this.id = id;
        this.name = name;
        this.datetime = datetime;
        this.latStart = latStart;
        this.latEnd = latEnd;
        this.longStart = longStart;
        this.longEnd = longEnd;
        this.rms = rms;
        this.rmsR1 = rmsR1;
        this.iri = iri;
        this.iriR1 = iriR1;
        this.classification = classification;
        this.classificationR1 = classificationR1;
        this.covAUD = covAUD;
        this.covBRL = covBRL;
        this.distance = distance;
        this.vehicleType = vehicleType;
        this.averageSpeed = averageSpeed;
        this.maxSpeed = maxSpeed;
    }

    public Survey(String name, String datetime, Double latStart, Double latEnd, Double longStart, Double longEnd, Double rms, Double rmsR1, Double iri, Double iriR1, String classification, String classificationR1, Double covAUD, Double covBRL, Double distance, String vehicleType, Double averageSpeed, Double maxSpeed) {
        this.name = name;
        this.datetime = datetime;
        this.latStart = latStart;
        this.latEnd = latEnd;
        this.longStart = longStart;
        this.longEnd = longEnd;
        this.rms = rms;
        this.rmsR1 = rmsR1;
        this.iri = iri;
        this.iriR1 = iriR1;
        this.classification = classification;
        this.classificationR1 = classificationR1;
        this.covAUD = covAUD;
        this.covBRL = covBRL;
        this.distance = distance;
        this.vehicleType = vehicleType;
        this.averageSpeed = averageSpeed;
        this.maxSpeed = maxSpeed;
    }

    public Survey() {
    }
}
