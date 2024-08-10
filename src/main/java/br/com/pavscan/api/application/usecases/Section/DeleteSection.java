package br.com.pavscan.api.application.usecases.Section;

import br.com.pavscan.api.application.gateways.SectionRepository;
import org.springframework.http.ResponseEntity;

public class DeleteSection {
    private final SectionRepository repository;

    public DeleteSection(SectionRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Object> deleteSection(Long id){ return repository.deleteSection(id); }
}
