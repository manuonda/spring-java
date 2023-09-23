package com.kafka.config;

import ch.qos.logback.classic.joran.serializedModel.HardenedModelInputStream;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    public Map<String, Object> producerProps(){
       HashMap<String, Object> props = new HashMap<>();
       props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
       props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
       props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
       return props;
   }


   @Bean
    public ProducerFactory<String, String> producerFactory(){
       return new DefaultKafkaProducerFactory<String, String>(producerProps());
   }


   @Bean
    public KafkaTemplate<String, String> objectKafkaTemplate(){
       return new KafkaTemplate<String,String>(producerFactory());
   }
}
