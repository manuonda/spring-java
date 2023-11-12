package com.project.two.producto.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.Topic;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.reposicion_producto}")
    private String kafkaTopicReposicionProducto;

    @Value("${spring.kafka.topic.agregar_producto}")
    private String kafkaTopicAgregarProducto;


    @Bean
    public NewTopic newTopicReposicionProducto(){
      return TopicBuilder
              .name(kafkaTopicReposicionProducto)
              .build();
    }

    @Bean
    public NewTopic newTopicKafkaAgregarProducto(){
        return TopicBuilder
                .name(kafkaTopicAgregarProducto)
                .build();
    }


}
