package com.navneet.kafka.config;

import com.navneet.kafka.constants.KafkaConstants;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author navneetprabhakar
 * Kafka Producer Configurations
 */
@Configuration
public class KafkaProducerConfig {

    @Autowired
    private KafkaConstants kafkaConstants;

    /**
     * Kafka Template Bean
     * @return
     */
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    /**
     * Kafka Producer Factory
     * @return
     */
    @Bean
    public ProducerFactory<String, Object> producerFactory(){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConstants.getBootstrapAddress());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        properties.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 26214400);
        return new DefaultKafkaProducerFactory<>(properties);
    }
}
