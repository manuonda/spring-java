package com.services.email;


import com.services.base.domain.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

   @KafkaListener(topics = "${spring.kafka.topic}",
           groupId = "${spring.kafka.consumer.group-id}"
   )
    public void consume(OrderEvent orderEvent) {
      logger.info("Order event received email received  => %s " +  orderEvent.toString());

      //save the order event into the database
   }
}
