package br.com.pavscan.api.application.usecases.Highway;

import br.com.pavscan.api.application.gateways.HighwayRepository;
import br.com.pavscan.api.domain.entities.highway.Highway;

public class CreateHighway {
    private final HighwayRepository repository;

    public CreateHighway(HighwayRepository repository){
        this.repository = repository;
    }
    public Highway createHighway(Highway highway){ return repository.createHighway(highway); }
}
