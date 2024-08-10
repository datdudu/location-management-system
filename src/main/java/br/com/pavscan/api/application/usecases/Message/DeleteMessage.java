package br.com.pavscan.api.application.usecases.Message;

import br.com.pavscan.api.application.gateways.MessageRepository;
import org.springframework.http.ResponseEntity;

public class DeleteMessage {
    private final MessageRepository repository;

    public DeleteMessage(MessageRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Object> deleteMessage(Long id){ return repository.deleteMessage(id); }
}
