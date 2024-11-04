package com.message_app.api_service.service;

import org.springframework.web.reactive.function.client.WebClient;

public class MessageService {

  @SuppressWarnings("unused")
  private final WebClient webClient;

  public MessageService(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder
      .baseUrl("http://backend-service:8080")
      .build();
  }
}
