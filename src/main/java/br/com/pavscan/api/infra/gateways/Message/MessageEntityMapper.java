package br.com.pavscan.api.infra.gateways.Message;

import br.com.pavscan.api.domain.entities.message.Message;
import br.com.pavscan.api.infra.persistence.Message.MessageEntity;

public class MessageEntityMapper {
    public MessageEntity toEntity(Message message){
        return new MessageEntity(
                message.getMessage(),
                message.getDescription());
    }

    public Message toDomain(MessageEntity messageEntity){
        return new Message(
                messageEntity.getMessage(),
                messageEntity.getDescription()
        );
    }
}
