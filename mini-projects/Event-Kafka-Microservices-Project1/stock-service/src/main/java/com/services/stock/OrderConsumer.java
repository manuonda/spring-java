package com.services.stock;

import com.services.base.domain.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics="${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    void listener(OrderEvent orderEvent) {
     logger.info("Order event received in stock service %s => " +  orderEvent.toString());

     // save the order event into the database

    }
}
