package br.com.pavscan.api.application.usecases.Section;

import br.com.pavscan.api.application.gateways.SectionRepository;
import br.com.pavscan.api.domain.entities.section.Section;
import org.springframework.http.ResponseEntity;

public class UpdateSection {
    private final SectionRepository repository;

    public UpdateSection(SectionRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Section> updateSection(Section section, Long id){ return repository.updateSection(section, id); }
}
