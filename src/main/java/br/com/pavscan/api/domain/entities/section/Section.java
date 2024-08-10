package br.com.pavscan.api.domain.entities.section;


import br.com.pavscan.api.domain.entities.survey.Survey;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Section {
    private Long id;
    private String key;
    private Long index;
    private String surveyName;
    private String vehicleType;
    private Double latStart;
    private Double latEnd;
    private Double longStart;
    private Double longEnd;
    private Double averageSpeed;
    private Double maxSpeed;
    private Double dist1;
    private Double dist2;
    private Double dist3;
    private Integer altitude;
    private Long dtStart;
    private Long dtEnd;
    private Double acc;
    private Integer size;
    private Double rms;
    private Double rmsR1;
    private Double iri;
    private Double iriR1;
    private String classification;
    private String classificationR1;
    private Double covAUD;
    private Double covBRL;
    private String email;
    private Long surveyId;
    private String imagePath;

    public Section(String key, Long index, String surveyName, String vehicleType, Double latStart, Double latEnd, Double longStart, Double longEnd, Double averageSpeed, Double maxSpeed, Double dist1, Double dist2, Double dist3, Integer altitude, Long dtStart, Long dtEnd, Double acc, Integer size, Double rms, Double rmsR1, Double iri, Double iriR1, String classification, String classificationR1, Double covAUD, Double covBRL, String email, Long surveyId, String imagePath) {
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
        this.surveyId = surveyId;
        this.imagePath = imagePath;
    }

    public Section(Long id, String key, Long index, String surveyName, String vehicleType, Double latStart, Double latEnd, Double longStart, Double longEnd, Double averageSpeed, Double maxSpeed, Double dist1, Double dist2, Double dist3, Integer altitude, Long dtStart, Long dtEnd, Double acc, Integer size, Double rms, Double rmsR1, Double iri, Double iriR1, String classification, String classificationR1, Double covAUD, Double covBRL, String email, Long surveyId, String imagePath) {
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
        this.surveyId = surveyId;
        this.imagePath = imagePath;
    }

    public Section() {
    }

    public Section(Long id, String key, Long index, String surveyName, String vehicleType, Double latStart, Double latEnd, Double longStart, Double longEnd, Double averageSpeed, Double maxSpeed, Double dist1, Double dist2, Double dist3, Integer altitude, Long dtStart, Long dtEnd, Double acc, Integer size, Double rms, Double rmsR1, Double iri, Double iriR1, String classification, String classificationR1, Double covAUD, Double covBRL, String email, String imagePath) {
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

    public Section(String key, Long index, String surveyName, String vehicleType, Double latStart, Double latEnd, Double longStart, Double longEnd, Double averageSpeed, Double maxSpeed, Double dist1, Double dist2, Double dist3, Integer altitude, Long dtStart, Long dtEnd, Double acc, Integer size, Double rms, Double rmsR1, Double iri, Double iriR1, String classification, String classificationR1, Double covAUD, Double covBRL, String email, String imagePath) {
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
}
