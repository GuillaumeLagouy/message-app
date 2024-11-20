package com.message_app.api_service.controller;

import com.message_app.api_service.dto.MessageDTO;
import com.message_app.api_service.dto.MessageRequest;
import com.message_app.api_service.service.MessageService;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MessageController {

  @Autowired
  private MessageService messageService;

  @Autowired
  private KafkaTemplate<String, String> createMessageKafkaTemplate;

  @Value(value = "${spring.kafka.topic}")
  private String topicName;

  @GetMapping("/messages")
  @CrossOrigin(origins = "http://localhost:3000")
  public Flux<MessageDTO> getAllMessages() {
    return messageService.getAllMessages();
  }

  @RequestMapping("/message")
  @CrossOrigin(origins = "http://localhost:3000")
  @ResponseStatus(HttpStatus.CREATED)
  public CompletableFuture<ResponseEntity<String>> createMessage(@RequestBody String content) {
    CompletableFuture<SendResult<String, String>> future = createMessageKafkaTemplate.send(topicName, content);

    return future.handle((result, ex) -> {
      if (ex == null) {
          System.out.println("Message sent: " + content);
          return ResponseEntity.status(HttpStatus.CREATED).body("Message sent successfully");
      } else {
          System.err.println("Failed to send message: " + ex.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message");
      }
  });
  }

  @PutMapping("/message/{id}")
  @CrossOrigin(origins = "http://localhost:3000")
  @ResponseStatus(HttpStatus.OK)
  public Mono<MessageDTO> updateMessage(
    @PathVariable Integer id,
    @RequestBody MessageRequest messageRequest
  ) {
    return messageService.updateMessage(id, messageRequest);
  }
}
