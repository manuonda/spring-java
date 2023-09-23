package com.kafka.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    @Value("${spring.kafka.topic.one}")
    String topicOne;

    @Value("${spring.kafka.topic.two}")
    String topicTwo;

    @Bean
    public NewTopic topicOne() {
        return TopicBuilder.name("one")
                .partitions(6)
                .replicas(3)
                .compact()
                .build();
    }

    @Bean
    public NewTopic topicTwo() {
        return TopicBuilder.name("two")
                .build();
    }

}
