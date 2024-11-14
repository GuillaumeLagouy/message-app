package com.message_app.api_service.controller;

import com.message_app.api_service.dto.MessageDTO;
import com.message_app.api_service.dto.MessageRequest;
import com.message_app.api_service.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MessageController {

  @Autowired
  private MessageService messageService;

  @GetMapping("/messages")
  @CrossOrigin(origins = "http://localhost:3000")
  public Flux<MessageDTO> getAllMessages() {
    return messageService.getAllMessages();
  }

  @PostMapping("/message")
  @CrossOrigin(origins = "http://localhost:3000")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Void> createMessage(@RequestBody MessageRequest messageRequest) {
    return messageService.createMessage(messageRequest);
  }
}
