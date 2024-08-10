package br.com.pavscan.api.application.usecases.Section;

import br.com.pavscan.api.application.gateways.SectionRepository;
import br.com.pavscan.api.domain.entities.section.Section;
import org.springframework.http.ResponseEntity;

public class CreateSection {
    private final SectionRepository repository;

    public CreateSection(SectionRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Section> createSection(Section section){ return repository.createSection(section); }
}
