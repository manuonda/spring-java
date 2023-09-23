package com.example;


import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class kafkaListeners {

    @KafkaListener(topics="amigoscode", groupId = "groupId")
    void listener(String data) {
        System.out.println("Listener received : " + data);

    }
}
