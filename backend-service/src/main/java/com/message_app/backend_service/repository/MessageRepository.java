package com.message_app.backend_service.repository;

import com.message_app.backend_service.entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository
  extends CrudRepository<MessageEntity, Integer> {}
