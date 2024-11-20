package com.message_app.backend_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message_app.backend_service.config.JacksonConfig;
import com.message_app.backend_service.dto.MessageKafkaRequest;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

  @Value(value = "${spring.kafka.bootstrap-servers}")
  private String bootstrapAddress;

  @Value(value = "${spring.kafka.consumer.groupId}")
  private String groupId;

  @Bean
  public ConsumerFactory<String, MessageKafkaRequest> consumerFactory() {
    Map<String, Object> props = new HashMap<>();

    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

    ObjectMapper objectMapper = new JacksonConfig().objectMapper();
    JsonDeserializer<MessageKafkaRequest> deserializer = new JsonDeserializer<>(
      MessageKafkaRequest.class,
      objectMapper
    );
    deserializer.trustedPackages("*");

    return new DefaultKafkaConsumerFactory<>(
      props,
      new StringDeserializer(),
      deserializer
    );
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<
    String,
    MessageKafkaRequest
  > kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<
      String,
      MessageKafkaRequest
    > factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}
