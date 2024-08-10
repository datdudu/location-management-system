package br.com.pavscan.api.infra.persistence.Highway;

import br.com.pavscan.api.infra.persistence.Survey.SurveyEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "highways")
@Setter
@Getter
@ToString
public class HighwayEntity {
    @Id
    @Column(name="highway_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer distance;

    @Column(nullable = false)
    private String coatingType;

    @Column(nullable = false)
    private Double latStart;

    @Column(nullable = false)
    private Double latEnd;

    @Column(nullable = false)
    private Double longStart;

    @Column(nullable = false)
    private Double longEnd;

    @OneToMany(mappedBy = "highway", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SurveyEntity> surveys = new ArrayList<>();



    public HighwayEntity(Long id, String name, Integer distance, String coatingType, Double latStart, Double latEnd, Double longStart, Double longEnd) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.coatingType = coatingType;
        this.latStart = latStart;
        this.latEnd = latEnd;
        this.longStart = longStart;
        this.longEnd = longEnd;
    }

    public HighwayEntity(String name, Integer distance, String coatingType, Double latStart, Double latEnd, Double longStart, Double longEnd) {
        this.name = name;
        this.distance = distance;
        this.coatingType = coatingType;
        this.latStart = latStart;
        this.latEnd = latEnd;
        this.longStart = longStart;
        this.longEnd = longEnd;
    }

    public HighwayEntity() {
    }
}
