package br.com.pavscan.api.domain.entities.section;

public class SectionFactory {
    private Section section;

    private Section withAllInformation(String key, Long index, String survey, String vehicleType, Double latStart, Double latEnd, Double longStart, Double longEnd, Double averageSpeed, Double maxSpeed, Double dist1, Double dist2, Double dist3, Integer altitude, Long dtStart, Long dtEnd, Double acc, Integer size, Double rms, Double rmsR1, Double iri, Double iriR1, String classification, String classificationR1, Double covAUD, Double covBRL, String email, String imagePath){
        this.section = new Section( key,  index,  survey, vehicleType,  latStart,  latEnd,  longStart,  longEnd,  averageSpeed,  maxSpeed,  dist1,  dist2,  dist3,  altitude,  dtStart,  dtEnd,  acc,  size,  rms,  rmsR1,  iri,  iriR1,  classification,  classificationR1,  covAUD,  covBRL,  email, imagePath );

        return this.section;
    }
}
