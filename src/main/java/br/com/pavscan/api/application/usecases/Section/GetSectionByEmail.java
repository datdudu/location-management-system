package br.com.pavscan.api.application.usecases.Section;

import br.com.pavscan.api.application.gateways.SectionRepository;
import br.com.pavscan.api.domain.entities.section.Section;
import org.springframework.http.ResponseEntity;

public class GetSectionByEmail {
    private final SectionRepository repository;

    public GetSectionByEmail(SectionRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Section> getSectionByEmail(String email){ return repository.getSectionByEmail(email); }
}
