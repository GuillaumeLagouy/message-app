package com.message_app.api_service.dto;

import java.time.LocalDateTime;

public class MessageDTO {

  private Long id;
  private LocalDateTime postedAt;
  private String message;

  public MessageDTO(Long id, LocalDateTime postedAt, String message) {
    this.id = id;
    this.postedAt = postedAt;
    this.message = message;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getPostedAt() {
    return postedAt;
  }

  public void setPostedAt(LocalDateTime postedAt) {
    this.postedAt = postedAt;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
