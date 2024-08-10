package br.com.pavscan.api.domain.entities.survey;

public class SurveyFactory {

    private Survey survey;

    private Survey withAllInformation(String name, String datetime, Double latStart, Double latEnd, Double longStart, Double longEnd, Double rms, Double rmsR1, Double iri, Double iriR1, String classification, String classificationR1, Double covAUD, Double covBRL, Double distance, String vehicleType, Double averageSpeed, Double maxSpeed){
        this.survey = new Survey(name, datetime, latStart, latEnd, longStart, longEnd, rms, rmsR1,iri,iriR1, classification, classificationR1, covAUD, covBRL, distance, vehicleType, averageSpeed, maxSpeed);

        return this.survey;
    }
}
