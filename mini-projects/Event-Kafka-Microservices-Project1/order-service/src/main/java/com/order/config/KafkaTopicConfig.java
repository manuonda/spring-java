package com.order.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {


    @Value("${}")
    @Bean
    public NewTopic topicCode(){
        return TopicBuilder
                .name("order_topics")
                .partitions(3)
                .build();
    }
}
