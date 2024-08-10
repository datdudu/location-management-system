package br.com.pavscan.api.application.usecases.Message;

import br.com.pavscan.api.application.gateways.MessageRepository;
import br.com.pavscan.api.domain.entities.message.Message;
import org.springframework.http.ResponseEntity;

public class UpdateMessage {

    private final MessageRepository repository;

    public UpdateMessage(MessageRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Message> updateMessage(Message message, Long id){ return repository.updateMessage(message, id); }
}
