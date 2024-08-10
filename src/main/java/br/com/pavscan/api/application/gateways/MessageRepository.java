package br.com.pavscan.api.application.gateways;

import br.com.pavscan.api.domain.entities.message.Message;
import org.springframework.http.ResponseEntity;

public interface MessageRepository {
    Message createMessage(Message message);

    ResponseEntity<Message> getMessageByName(String name);

    ResponseEntity<Message> updateMessage(Message message, Long id);

    ResponseEntity<Object> deleteMessage(Long id);
}
