package br.com.pavscan.api.infra.persistence.Section;

import br.com.pavscan.api.infra.persistence.Survey.SurveyEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "sections")
@Setter
@Getter
@ToString
public class SectionEntity {
    @Id
    @Column(name="section_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String key;

    @Column(nullable = false)
    private Long index;

    @Column(nullable = false)
    private String surveyName;

    @Column(nullable = false)
    private String vehicleType;

    @Column(nullable = false)
    private Double latStart;

    @Column(nullable = false)
    private Double latEnd;

    @Column(nullable = false)
    private Double longStart;

    @Column(nullable = false)
    private Double longEnd;

    @Column(nullable = false)
    private Double averageSpeed;

    @Column(nullable = false)
    private Double maxSpeed;

    private Double dist1;

    private Double dist2;

    private Double dist3;

    private Integer altitude;

    @Column(nullable = false)
    private Long dtStart;

    @Column(nullable = false)
    private Long dtEnd;

    private Double acc;

    private Integer size;

    @Column(nullable = false)
    private Double rms;

    @Column(nullable = false)
    private Double rmsR1;

    @Column(nullable = false)
    private Double iri;

    @Column(nullable = false)
    private Double iriR1;

    private String classification;

    private String classificationR1;

    private Double covAUD;

    private Double covBRL;

    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    private SurveyEntity survey;

    private String email;

    private String imagePath;

    public SectionEntity(Long id, String key, Long index, String surveyName, String vehicleType, Double latStart, Double latEnd, Double longStart, Double longEnd, Double averageSpeed, Double maxSpeed, Double dist1, Double dist2, Double dist3, Integer altitude, Long dtStart, Long dtEnd, Double acc, Integer size, Double rms, Double rmsR1, Double iri, Double iriR1, String classification, String classificationR1, Double covAUD, Double covBRL, String email, String imagePath) {
        this.id = id;
        this.key = key;
        this.index = index;
        this.surveyName = surveyName;
        this.vehicleType = vehicleType;
        this.latStart = latStart;
        this.latEnd = latEnd;
        this.longStart = longStart;
        this.longEnd = longEnd;
        this.averageSpeed = averageSpeed;
        this.maxSpeed = maxSpeed;
        this.dist1 = dist1;
        this.dist2 = dist2;
        this.dist3 = dist3;
        this.altitude = altitude;
        this.dtStart = dtStart;
        this.dtEnd = dtEnd;
        this.acc = acc;
        this.size = size;
        this.rms = rms;
        this.rmsR1 = rmsR1;
        this.iri = iri;
        this.iriR1 = iriR1;
        this.classification = classification;
        this.classificationR1 = classificationR1;
        this.covAUD = covAUD;
        this.covBRL = covBRL;
        this.email = email;
        this.imagePath = imagePath;
    }


    public SectionEntity(String key, Long index, String surveyName, String vehicleType, Double latStart, Double latEnd, Double longStart, Double longEnd, Double averageSpeed, Double maxSpeed, Double dist1, Double dist2, Double dist3, Integer altitude, Long dtStart, Long dtEnd, Double acc, Integer size, Double rms, Double rmsR1, Double iri, Double iriR1, String classification, String classificationR1, Double covAUD, Double covBRL, String email, String imagePath) {
        this.key = key;
        this.index = index;
        this.surveyName = surveyName;
        this.vehicleType = vehicleType;
        this.latStart = latStart;
        this.latEnd = latEnd;
        this.longStart = longStart;
        this.longEnd = longEnd;
        this.averageSpeed = averageSpeed;
        this.maxSpeed = maxSpeed;
        this.dist1 = dist1;
        this.dist2 = dist2;
        this.dist3 = dist3;
        this.altitude = altitude;
        this.dtStart = dtStart;
        this.dtEnd = dtEnd;
        this.acc = acc;
        this.size = size;
        this.rms = rms;
        this.rmsR1 = rmsR1;
        this.iri = iri;
        this.iriR1 = iriR1;
        this.classification = classification;
        this.classificationR1 = classificationR1;
        this.covAUD = covAUD;
        this.covBRL = covBRL;
        this.email = email;
        this.imagePath = imagePath;
    }

    public SectionEntity() {

    }
}
