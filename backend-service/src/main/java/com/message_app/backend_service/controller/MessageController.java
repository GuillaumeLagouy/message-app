package com.message_app.backend_service.controller;

import com.message_app.backend_service.dto.MessageDTO;
import com.message_app.backend_service.entity.MessageEntity;
import com.message_app.backend_service.repository.MessageRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/messages")
public class MessageController {

  @Autowired
  private MessageRepository messageRepository;

  @PostMapping(path = "/create")
  public @ResponseBody ResponseEntity<MessageDTO> createNewMessage(
    @RequestBody MessageRequest request
  ) {
    MessageEntity messageEntity = new MessageEntity();
    messageEntity.setPostedAt(LocalDateTime.now());
    messageEntity.setMessage(request.getContent());

    MessageEntity savedMessage = messageRepository.save(messageEntity);

    MessageDTO response = new MessageDTO(
      savedMessage.getId(),
      savedMessage.getPostedAt(),
      savedMessage.getMessage()
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/update/{id}")
  public @ResponseBody ResponseEntity<MessageDTO> updateMessage(
    @PathVariable Integer id,
    @RequestBody MessageRequest request
  ) {
    MessageEntity messageEntity = messageRepository.findById(id).orElse(null);
    if (messageEntity != null) {
      messageEntity.setPostedAt(LocalDateTime.now());
      messageEntity.setMessage(request.getContent());

      MessageEntity savedMessage = messageRepository.save(messageEntity);

      MessageDTO response = new MessageDTO(
        savedMessage.getId(),
        savedMessage.getPostedAt(),
        savedMessage.getMessage()
      );

      return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<MessageEntity> getAllMessages() {
    return messageRepository.findAll();
  }
}
