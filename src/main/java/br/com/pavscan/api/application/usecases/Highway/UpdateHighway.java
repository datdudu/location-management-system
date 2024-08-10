package br.com.pavscan.api.application.usecases.Highway;

import br.com.pavscan.api.application.gateways.HighwayRepository;
import br.com.pavscan.api.domain.entities.highway.Highway;
import org.springframework.http.ResponseEntity;

public class UpdateHighway {
    private final HighwayRepository repository;

    public UpdateHighway(HighwayRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Highway> updateHighway(Highway highway, Long id){ return repository.updateHighway(highway, id); }
}
