package com.message_app.api_service.controller;

import com.message_app.api_service.dto.MessageDTO;
import com.message_app.api_service.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class MessageController {

  @Autowired
  private MessageService messageService;

  @GetMapping("/messages")
  @CrossOrigin(origins = "http://localhost:3000")
  public Flux<MessageDTO> getAllMessages() {
    return messageService.getAllMessages();
  }
}
