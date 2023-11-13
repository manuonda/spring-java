package com.project.two.inventario.config;


import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.event.ConsumerPausedEvent;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerKafkaConfig {


   @Value("${spring.kafka.consumer.bootstrap-servers}")
   private String bootStrapServers;
   public Map<String, Object> consumerConfig(){
      Map<String, Object> props = new HashMap<>();
      props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
      props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
      props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
      return props;
   }

   @Bean
   public ConsumerFactory<String, Object> consumerFactory(){
      return new DefaultKafkaConsumerFactory<>( consumerConfig(), new StringDeserializer(), new JsonDeserializer<>(Object.class));
   }

   @Bean
   public ConcurrentKafkaListenerContainerFactory<String, Object> orderListenerContainerFactory() {
      ConcurrentKafkaListenerContainerFactory<String, Object> factory =
              new ConcurrentKafkaListenerContainerFactory<>();
      factory.setConsumerFactory(consumerFactory());
      return factory;
   }



}
