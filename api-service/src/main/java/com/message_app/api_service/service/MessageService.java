package com.message_app.api_service.service;

import com.message_app.api_service.dto.MessageDTO;
import com.message_app.api_service.dto.MessageRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageService {

  private final WebClient webClient;

  // Liste temporaire de messages pour simuler l'existence d'un backend
  private List<MessageDTO> mockMessages = new ArrayList<>();

  public MessageService(WebClient.Builder webClientBuilder) {
    this.webClient = (WebClient) webClientBuilder
      .baseUrl("http://backend-service:8081")
      .build();
  }

  public Optional<MessageDTO> getMessageById(Integer id) {
    return mockMessages
      .stream()
      .filter(message -> message.getId().equals(id))
      .findFirst();
  }

  public Flux<MessageDTO> getAllMessages() {
    return webClient
      .get()
      .uri("/messages/all")
      .retrieve()
      .bodyToFlux(MessageDTO.class);
  }

  public Mono<Void> createMessage(MessageRequest request) {
    return webClient
      .post()
      .uri("/messages/create")
      .bodyValue(request)
      .retrieve()
      .bodyToMono(Void.class);
  }
}
