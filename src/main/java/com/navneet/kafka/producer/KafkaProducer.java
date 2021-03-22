package com.navneet.kafka.producer;

import java.util.List;

/**
 * @author navneetprabhakar
 * Kafka Message Producer Interface
 */
public interface KafkaProducer {
    /**
     * This method sends single message to single topic
     * @param topic
     * @param value
     * @param <V>
     * @throws Exception
     */
    <V> void sendToKafka(String topic, V value) throws Exception;

    /**
     * This method sends multiple messages to single topic
     * @param topic
     * @param values
     * @param <V>
     * @throws Exception
     */
    <V> void sendToKafka(String topic, List<V> values) throws Exception;

}
