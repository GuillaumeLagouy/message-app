package com.message_app.backend_service.dto;

import java.time.LocalDateTime;

public class MessageDTO {

  private Integer id;
  private LocalDateTime postedAt;
  private String message;

  public MessageDTO(Integer id, LocalDateTime postedAt, String message) {
    this.id = id;
    this.postedAt = postedAt;
    this.message = message;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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
