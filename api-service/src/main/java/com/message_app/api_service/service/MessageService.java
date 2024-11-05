package com.message_app.api_service.service;

import com.message_app.api_service.dto.MessageDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  // Liste temporaire de messages pour simuler l'existence d'un backend
  private List<MessageDTO> mockMessages = new ArrayList<>();

  public MessageService() {
    mockMessages.add(
      new MessageDTO(1L, LocalDateTime.now(), "Bonjour ceci est un message")
    );
    mockMessages.add(
      new MessageDTO(2L, LocalDateTime.now(), "Ceci est un autre message")
    );
  }

  public Optional<MessageDTO> getMessageById(Long id) {
    return mockMessages
      .stream()
      .filter(message -> message.getId().equals(id))
      .findFirst();
  }

  public List<MessageDTO> getAllMessages() {
    return mockMessages;
  }

  public void createMessage(String message) {
    mockMessages.add(new MessageDTO(null, LocalDateTime.now(), message));
  }
}
