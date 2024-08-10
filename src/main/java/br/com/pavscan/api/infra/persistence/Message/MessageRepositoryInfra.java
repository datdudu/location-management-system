package br.com.pavscan.api.infra.persistence.Message;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepositoryInfra extends JpaRepository<MessageEntity, Long> {
    MessageEntity findByMessage(String message);
}