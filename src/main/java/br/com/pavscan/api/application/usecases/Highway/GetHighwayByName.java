package br.com.pavscan.api.application.usecases.Highway;

import br.com.pavscan.api.application.gateways.HighwayRepository;
import br.com.pavscan.api.domain.entities.highway.Highway;
import org.springframework.http.ResponseEntity;

public class GetHighwayByName {
    private final HighwayRepository repository;

    public GetHighwayByName(HighwayRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Highway> getHighwayByName(String message){ return repository.getHighwayByName(message); }
}
