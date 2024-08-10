package br.com.pavscan.api.domain.entities.message;

public class MessageFabric {
    private Message message;

    public Message withNameDescription(String message, String description){
        this.message = new Message(message, description);

        return this.message;
    }
}
