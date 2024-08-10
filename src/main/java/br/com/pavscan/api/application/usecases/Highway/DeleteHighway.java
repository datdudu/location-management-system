package br.com.pavscan.api.application.usecases.Highway;

import br.com.pavscan.api.application.gateways.HighwayRepository;
import org.springframework.http.ResponseEntity;

public class DeleteHighway {
    private final HighwayRepository repository;

    public DeleteHighway(HighwayRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Object> deleteHighway(Long id){ return repository.deleteHighway(id); }
}
