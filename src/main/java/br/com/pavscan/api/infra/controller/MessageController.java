package br.com.pavscan.api.infra.controller;

import br.com.pavscan.api.application.usecases.Message.CreateMessage;
import br.com.pavscan.api.application.usecases.Message.DeleteMessage;
import br.com.pavscan.api.application.usecases.Message.GetMessageByName;
import br.com.pavscan.api.application.usecases.Message.UpdateMessage;
import br.com.pavscan.api.domain.entities.message.Message;
import br.com.pavscan.api.infra.controller.Dtos.ChatRequest;
import br.com.pavscan.api.infra.controller.Dtos.ChatResponse;
import br.com.pavscan.api.infra.controller.Dtos.MessageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/messages")
@SecurityRequirement(name = "bearer-key")
public class MessageController {
    @Value("${python.app.url}")
    private String pythonAppUrl;  // URL of your Python application

    private final RestTemplate restTemplate;

    private final CreateMessage createMessage;
    private final GetMessageByName getMessageByName;

    private final UpdateMessage updateMessage;

    private final DeleteMessage deleteMessage;

    public MessageController(RestTemplate restTemplate, CreateMessage createMessage, GetMessageByName getMessageByName, UpdateMessage updateMessage, DeleteMessage deleteMessage){
        this.restTemplate = restTemplate;
        this.createMessage = createMessage;
        this.getMessageByName = getMessageByName;
        this.updateMessage = updateMessage;
        this.deleteMessage = deleteMessage;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register a message", description = "Register a message with a description related",
                tags = {"Message"},
                responses = {
                        @ApiResponse(responseCode = "201", ref = "#/components/responses/Message201Response")
                })
    public MessageDto registerMessage(@RequestBody MessageDto messageDto){
        Message save = createMessage.createMessage(new Message(messageDto.message(), messageDto.description()));

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Registering a new message" + localDateTime);

        return new MessageDto(save.getMessage(), save.getDescription());
    }

    @Operation(summary = "Get a message by a word", description = "You send a message and it returns the message with the description related",
            tags = {"Message"},
            responses = {
                    @ApiResponse(responseCode = "200", ref = "#/components/responses/Message200Response"),
                    @ApiResponse(responseCode = "404", ref = "#/components/responses/404Response")
            })
    @GetMapping("{message}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageDto> getMessageByName(@PathVariable ( value = "message") String message) {
        ResponseEntity<Message> messageRequested = getMessageByName.getMessageByName(message);
        if(messageRequested.hasBody()){
            MessageDto messageDto = new MessageDto(messageRequested.getBody().getMessage(), messageRequested.getBody().getDescription());
            return ResponseEntity.ok(messageDto);
        }

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Getting a message by name " + localDateTime);

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Updated a message", description = "Giving a Id and a new message, the message related to the id will be updated",
            tags = {"Message"},
            responses = {
                    @ApiResponse(responseCode = "200", ref = "#/components/responses/Message200Response"),
                    @ApiResponse(responseCode = "404", ref = "#/components/responses/404Response")
            })
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageDto> updateMessage(@RequestBody MessageDto messageDto, @PathVariable(value = "id") Long id){
        Message messageToUpdate = new Message(messageDto.message(), messageDto.description());

        updateMessage.updateMessage(messageToUpdate, id);

        MessageDto messageUpdatedDto = new MessageDto(messageToUpdate.getMessage(), messageToUpdate.getDescription());

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Updating a message " + localDateTime);

        return ResponseEntity.ok(messageUpdatedDto);
    }

    @Operation(summary = "Delete a message by a Id", description = "Passing a Id, the message related to that Id will be deleted",
            tags = {"Message"},
            responses = {
                    @ApiResponse(responseCode = "204", ref = "#/components/responses/204Response"),
                    @ApiResponse(responseCode = "404", ref = "#/components/responses/404Response")
            })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteMessage(@PathVariable ( value = "id") Long id) {

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Deleting a message " + localDateTime);

        return deleteMessage.deleteMessage(id);
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessageToPython(@RequestBody String message) {
        // Define the endpoint on the Python application
        String pythonEndpoint = pythonAppUrl + "/chat";

        // Create the request body
        ChatRequest chatRequest = new ChatRequest(message);

        // Send POST request to Python application
        ResponseEntity<ChatResponse> responseEntity = restTemplate.postForEntity(
                pythonEndpoint,
                chatRequest,
                ChatResponse.class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            ChatResponse responseBody = responseEntity.getBody();
            return ResponseEntity.ok(responseBody.response());
        } else {
            return ResponseEntity.status(responseEntity.getStatusCode()).body("Failed to send message to Python app");
        }
    }
}
