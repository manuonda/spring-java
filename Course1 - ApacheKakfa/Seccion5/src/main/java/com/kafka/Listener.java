package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

@Component
public class Listener {


     private static final Logger logger = LoggerFactory.getLogger(Listener.class);

    /**
     * Batch with containerFactory
     * @param data
     */
    /*@KafkaListener(topics="topicOne", groupId="groupManuonda" ,
            containerFactory = "listenerContainerFactory",
            properties = {
                  "max.poll.interval.ms:4000",
                  "max.poll.records:10"
          }
    )
    public void listener(List<String> data){
       System.out.println("data : "+ data);
       data.forEach( x ->  {
           System.out.println(x);
       });
    }*/

    @KafkaListener(
            id = "manuonda", autoStartup ="true",
            topics="topicOne", groupId="groupManuonda" ,
            containerFactory = "listenerContainerFactory",
            properties = {
                    "max.poll.interval.ms:4000",
                    "max.poll.records:10"
            }
    )
    public void listener2(List<ConsumerRecord<String,String>> messages){
        System.out.println(" Data List<ConsumerRecord> : "+ messages);
        for(ConsumerRecord<String, String> message: messages) {
            logger.info("Partition = {} , offset={}, key = {} , Value = {} ",
                    message.partition(), message.offset(), message.key(), message.value());
            /*System.out.printf("Partition = {%d} , offset={%s}, key = {%s} , Value = {%s} ",
                    message.partition(), message.offset(), message.key(), message.value());
            System.out.println("---");*/
        }
    }
}
