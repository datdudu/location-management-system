package br.com.pavscan.api.infra.persistence.Survey;

import br.com.pavscan.api.infra.persistence.Authentication.UserEntity;
import br.com.pavscan.api.infra.persistence.Highway.HighwayEntity;
import br.com.pavscan.api.infra.persistence.Section.SectionEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "surveys")
@Setter
@Getter
@ToString
public class SurveyEntity {
    @Id
    @Column(name="survey_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String datetime;

    @Column(nullable = false)
    private Double latStart;

    @Column(nullable = false)
    private Double latEnd;

    @Column(nullable = false)
    private Double longStart;

    @Column(nullable = false)
    private Double longEnd;

    @Column(nullable = false)
    private Double rms;

    @Column(nullable = false)
    private Double rmsR1;

    @Column(nullable = false)
    private Double iri;

    @Column(nullable = false)
    private Double iriR1;

    @Column(nullable = false)
    private String classification;

    @Column(nullable = false)
    private String classificationR1;

    private Double covAUD;

    private Double covBRL;

    private Double distance;

    @Column(nullable = false)
    private String vehicleType;

    private Double averageSpeed;

    private Double maxSpeed;

    @ManyToOne
    @JoinColumn(name = "highway_id")
    private HighwayEntity highway;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SectionEntity> sections = new ArrayList<>();

    public SurveyEntity() {
    }

    public SurveyEntity(Long id, String name, String datetime, Double latStart, Double latEnd, Double longStart, Double longEnd, Double rms, Double rmsR1, Double iri, Double iriR1, String classification, String classificationR1, Double covAUD, Double covBRL, Double distance, String vehicleType, Double averageSpeed, Double maxSpeed) {
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

    public SurveyEntity(String name, String datetime, Double latStart, Double latEnd, Double longStart, Double longEnd, Double rms, Double rmsR1, Double iri, Double iriR1, String classification, String classificationR1, Double covAUD, Double covBRL, Double distance, String vehicleType, Double averageSpeed, Double maxSpeed) {
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
}
