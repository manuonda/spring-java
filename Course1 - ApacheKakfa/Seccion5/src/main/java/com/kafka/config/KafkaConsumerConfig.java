package com.kafka.config;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.MicrometerConsumerListener;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    private final PrometheusMeterRegistry prometheusMeterRegistry;

    public KafkaConsumerConfig(PrometheusMeterRegistry prometheusMeterRegistry) {
        this.prometheusMeterRegistry = prometheusMeterRegistry;
    }


    public Map<String, Object> consumerProps () {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"groupManuonda");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }


    @Bean
    public ConsumerFactory<String, Object> consumerFactory () {
        DefaultKafkaConsumerFactory consumerFactory = new DefaultKafkaConsumerFactory<>(consumerProps());
        consumerFactory.addListener(new MicrometerConsumerListener<String,String>(prometheusMeterRegistry));
        return new DefaultKafkaConsumerFactory<>(consumerProps());
    }

    /**
     * Al ser concurrentKafka consume messages de forma
     * concurrente
     * @return
     */
    @Bean(name="listenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(true); //procesar batcher
        factory.setConcurrency(2);  // 3 hilos
        return factory;
    }


}
