package br.com.pavscan.api.infra.config;

import br.com.pavscan.api.application.gateways.MessageRepository;
import br.com.pavscan.api.application.usecases.Message.CreateMessage;
import br.com.pavscan.api.application.usecases.Message.DeleteMessage;
import br.com.pavscan.api.application.usecases.Message.GetMessageByName;
import br.com.pavscan.api.application.usecases.Message.UpdateMessage;
import br.com.pavscan.api.infra.gateways.Message.MessageEntityMapper;
import br.com.pavscan.api.infra.gateways.Message.MessageRepositoryJpa;
import br.com.pavscan.api.infra.persistence.Message.MessageRepositoryInfra;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Bean
    CreateMessage createMessage(MessageRepository messageRepository){
        return new CreateMessage(messageRepository);
    }

    @Bean
    GetMessageByName getMessageByName(MessageRepository messageRepository){
        return new GetMessageByName(messageRepository);
    }

    @Bean
    UpdateMessage updateMessage(MessageRepository messageRepository){
        return new UpdateMessage(messageRepository);
    }

    @Bean
    DeleteMessage deleteMessage(MessageRepository messageRepository){
        return new DeleteMessage(messageRepository);
    }
    @Bean
    MessageRepositoryJpa createMessageRepositoryJpa(MessageRepositoryInfra repository, MessageEntityMapper mapper){
        return new MessageRepositoryJpa(repository, mapper);
    }

    @Bean
    MessageEntityMapper returnMessageMapper(){
        return new MessageEntityMapper();
    }
}
