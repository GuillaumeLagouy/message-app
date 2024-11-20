package com.message_app.api_service.dto;

import java.util.Optional;

public class MessageKafkaRequest {
    private Optional<Integer> id;
    private String content;

    public Optional<Integer> getId(){
        return id;
    }

    public void setId(Optional<Integer> id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
