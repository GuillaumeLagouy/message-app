package com.message_app.api_service.controller;

import com.message_app.api_service.dto.MessageDTO;
import com.message_app.api_service.dto.MessageKafkaRequest;
import com.message_app.api_service.service.MessageService;

import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class MessageController {

  @Autowired
  private MessageService messageService;

  @Autowired
  private KafkaTemplate<String, MessageKafkaRequest> kafkaTemplate;

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
    MessageKafkaRequest request = new MessageKafkaRequest();
    request.setContent(content);
    
    CompletableFuture<SendResult<String, MessageKafkaRequest>> future = kafkaTemplate.send(topicName, request);

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

  @RequestMapping("/message/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CompletableFuture<ResponseEntity<String>> updateMessage(@PathVariable Optional<Integer> id, @RequestBody String content) {
    MessageKafkaRequest request = new MessageKafkaRequest();
    request.setId(id);
    request.setContent(content);

    CompletableFuture<SendResult<String, MessageKafkaRequest>> future = kafkaTemplate.send(topicName, request);
    
    return future.handle((result, ex) -> {
      if (ex == null) {
          System.out.println("Message update: " + content);
          return ResponseEntity.status(HttpStatus.OK).body("Message update successfully");
      } else {
          System.err.println("Failed to update message: " + ex.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update message");
      }
    });
  }
}
