package br.com.pavscan.api.application.usecases.Message;

import br.com.pavscan.api.application.gateways.MessageRepository;
import br.com.pavscan.api.domain.entities.message.Message;
import org.springframework.http.ResponseEntity;

public class GetMessageByName {
    private final MessageRepository repository;

    public GetMessageByName(MessageRepository repository){
        this.repository = repository;
    }
    public ResponseEntity<Message> getMessageByName(String message){ return repository.getMessageByName(message); }
}
