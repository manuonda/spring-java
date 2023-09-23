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
    public NewTopic one() {
        return TopicBuilder.name("one")
                .build();
    }


    @Bean
    public NewTopic amigosCodeTopic(){
        return TopicBuilder.name("amigoscode")
                .build();
    }


    @Bean
    public NewTopic manuonda32(){
        return TopicBuilder.name("manuonda32")
                .partitions(6)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic prueba2(){
        return TopicBuilder.name("prueba2")
                .partitions(6)
                .replicas(3)
                .build();
    }
}
