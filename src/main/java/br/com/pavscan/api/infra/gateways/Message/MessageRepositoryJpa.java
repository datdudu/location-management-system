package br.com.pavscan.api.infra.gateways.Message;

import br.com.pavscan.api.application.gateways.MessageRepository;
import br.com.pavscan.api.domain.entities.message.Message;
import br.com.pavscan.api.infra.persistence.Message.MessageEntity;
import br.com.pavscan.api.infra.persistence.Message.MessageRepositoryInfra;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class MessageRepositoryJpa implements MessageRepository {
    private final MessageRepositoryInfra repository;
    private final MessageEntityMapper mapper;
    public MessageRepositoryJpa(MessageRepositoryInfra repository, MessageEntityMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Message createMessage(Message message) {
        MessageEntity entity = mapper.toEntity(message);
        repository.save(entity);

        return mapper.toDomain(entity);
    }

    @Override
    public ResponseEntity<Message> getMessageByName(String name) {

        MessageEntity entity = repository.findByMessage(name);

        if (entity != null) {

            return ResponseEntity.ok(mapper.toDomain(entity));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Message> updateMessage(Message message, Long id) {
        Optional<MessageEntity> messageEntity = repository.findById(id);

        if(messageEntity.isPresent()){
            messageEntity.get().setMessage(message.getMessage());
            messageEntity.get().setDescription(message.getDescription());

            repository.save(messageEntity.get());

            return ResponseEntity.ok(mapper.toDomain(messageEntity.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Object> deleteMessage(Long id) {
        Optional<MessageEntity> messageEntity = repository.findById(id);

        if(messageEntity.isPresent()) {
            repository.delete(messageEntity.get());

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
