package com.navneet.kafka.service;

import com.navneet.kafka.model.KafkaMessageRequest;

/**
 * @author navneetprabhakar
 */
public interface KafkaService {

    /**
     * This method pushes the message object to Kafka Topic
     * @param message
     */
    void pushMessageToKafka(KafkaMessageRequest message);

    /**
     * This method processes the received message
     * @param message
     */
    void processReceivedMessage(KafkaMessageRequest message);
}
