package com.kafka.config;

import io.micrometer.core.instrument.ImmutableTag;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.MicrometerProducerListener;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

   // private final CompositeMeterRegistry compositeMeterRegistry;

    private final PrometheusMeterRegistry prometheusMeterRegistry;

    public KafkaProducerConfig(PrometheusMeterRegistry prometheusMeterRegistry) {
        this.prometheusMeterRegistry = prometheusMeterRegistry;
    }


    public Map<String, Object> producerProps(){
       HashMap<String, Object> props = new HashMap<>();
       props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
       props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
       props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
       return props;
   }


   @Bean
    public ProducerFactory<String, String> producerFactory(){
        DefaultKafkaProducerFactory<String, String> defaultKafkaProducerFactory = new DefaultKafkaProducerFactory<>(producerProps());
       defaultKafkaProducerFactory.addListener(new MicrometerProducerListener<String, String>(prometheusMeterRegistry,
               Collections.singletonList(new ImmutableTag("customTag", "customTagValue"))));
        return defaultKafkaProducerFactory;
   }


   @Bean
    public KafkaTemplate<String, String> objectKafkaTemplate(){
       return new KafkaTemplate<String,String>(producerFactory());
   }
}
