package com.order.service;


import com.services.base.domain.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducerServiceImpl {

    private Logger logger = LoggerFactory.getLogger(OrderProducerServiceImpl.class);
   private NewTopic orderTopic;

   private KafkaTemplate<String, Object> kafkaTemplate;

    public OrderProducerServiceImpl(NewTopic orderTopic, KafkaTemplate<String, Object> kafkaTemplate) {
        this.orderTopic = orderTopic;
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(OrderEvent event) {
        logger.info("Order event => %s " , event.toString());
        //create Message

        Message<OrderEvent> message  = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC , orderTopic.name())
                .build();
        // kafka template send
        kafkaTemplate.send(message);
    }

}
