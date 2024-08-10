package br.com.pavscan.api.application.usecases.Highway;

import br.com.pavscan.api.application.gateways.HighwayRepository;
import br.com.pavscan.api.domain.entities.highway.Highway;
import org.springframework.http.ResponseEntity;

public class GetHighwayById {
    private final HighwayRepository repository;
    public GetHighwayById(HighwayRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Highway> getHighwayById(Long id){ return repository.getHighwayById(id); }
}
