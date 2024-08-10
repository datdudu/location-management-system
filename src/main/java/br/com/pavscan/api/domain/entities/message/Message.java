package br.com.pavscan.api.domain.entities.message;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Message {
    @Setter
    @Getter
    private String message;

    @Setter
    @Getter
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Message(String message, String description) {
        this.message = message;
        this.description = description;
    }
}
