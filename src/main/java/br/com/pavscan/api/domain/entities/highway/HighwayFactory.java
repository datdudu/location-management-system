package br.com.pavscan.api.domain.entities.highway;

public class HighwayFactory {
    private Highway highway;

    public Highway withAllInformations(String name, Integer distance, String coatingType, Double latStart, Double latEnd, Double longStart, Double longEnd){
        this.highway = new Highway(name, distance, coatingType, latStart, latEnd, longStart, longEnd);

        return this.highway;
    }
}
