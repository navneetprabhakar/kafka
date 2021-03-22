package com.navneet.kafka.config;

import com.navneet.kafka.constants.KafkaConstants;
import com.navneet.kafka.model.KafkaMessageRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author navneetprabhakar
 * Kafka Consumer Configurations
 */
@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    @Autowired
    private KafkaConstants kafkaConstants;

    /**
     * Kafka Listener Factory Bean
     * @return
     */
    @Bean(name = "kafkaTopic")
    public ConcurrentKafkaListenerContainerFactory<String, KafkaMessageRequest> kafkaFactory(){
        ConcurrentKafkaListenerContainerFactory<String, KafkaMessageRequest> config=new ConcurrentKafkaListenerContainerFactory<>();
        config.setConsumerFactory(consumerFactory());
        config.setConcurrency(kafkaConstants.getConcurrency());
        config.setBatchListener(true);
        config.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        config.getContainerProperties().setPollTimeout(kafkaConstants.getPollTimeout());
        return config;
    }

    /**
     * Kafka Consumer Factory
     * @return
     */
    @Bean
    public ConsumerFactory<String, KafkaMessageRequest> consumerFactory(){
        Map<String, Object> properties= new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConstants.getBootstrapAddress());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConstants.getAutoOffsetResetConfig());
        properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG,
                "org.apache.kafka.clients.consumer.RoundRobinAssignor");
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, kafkaConstants.getMaxPollRecordsConfig());
        return new DefaultKafkaConsumerFactory<>(properties, new StringDeserializer(), new JsonDeserializer<>(KafkaMessageRequest.class));
    }
}
