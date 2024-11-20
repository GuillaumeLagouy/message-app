package com.message_app.backend_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class BackendServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(BackendServiceApplication.class, args);
  }

  @KafkaListener(topics = "message-topic", groupId = "backend-consumer")
  public void listenGroupBackend(String message) {
    System.out.println("Received Message in group backend: " + message);
  }
}
