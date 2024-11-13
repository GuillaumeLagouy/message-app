package com.message_app.backend_service.controller;

import com.message_app.backend_service.entity.MessageEntity;
import com.message_app.backend_service.repository.MessageRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/messages")
public class MessageController {

  @Autowired
  private MessageRepository messageRepository;

  @PostMapping(path = "/create")
  public @ResponseBody String createNewMessage(@RequestParam String message) {
    MessageEntity m = new MessageEntity();
    m.setPostedAt(LocalDateTime.now());
    m.setMessage(message);
    messageRepository.save(m);

    return "Saved !";
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<MessageEntity> getAllMessages() {
    return messageRepository.findAll();
  }
}
