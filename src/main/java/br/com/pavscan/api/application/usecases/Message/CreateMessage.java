package br.com.pavscan.api.application.usecases.Message;

import br.com.pavscan.api.application.gateways.MessageRepository;
import br.com.pavscan.api.domain.entities.message.Message;

public class CreateMessage {

    private final MessageRepository repository;

    public CreateMessage(MessageRepository repository){
        this.repository = repository;
    }
    public Message createMessage(Message message){ return repository.createMessage(message); }

}
