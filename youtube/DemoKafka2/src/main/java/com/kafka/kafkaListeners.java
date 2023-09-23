package com.kafka;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id="multigropu", topics = {"prueba2","manuonda32"})
public class kafkaListeners {

   @KafkaHandler
    void stringHandler(Integer in) {
       System.out.println("Integer " + in);
   }

   @KafkaHandler
    void doubleHandler(Double in) {
       System.out.println("Double: "+ in);
   }

   @KafkaHandler
    void stringHandler(String in) {
       System.out.println("String : "+ in);
   }
}
