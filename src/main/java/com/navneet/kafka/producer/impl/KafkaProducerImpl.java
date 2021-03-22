package com.navneet.kafka.producer.impl;

import com.navneet.kafka.producer.KafkaProducer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author navneetprabhakar
 */
@Service
@Log4j2
public class KafkaProducerImpl implements KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * This method sends single message to single topic
     *
     * @param topic
     * @param value
     * @throws Exception
     */
    @Override
    public <V> void sendToKafka(String topic, V value) throws Exception {
        kafkaTemplate.send(topic, value).get();
    }

    /**
     * This method sends multiple messages to single topic
     *
     * @param topic
     * @param values
     * @throws Exception
     */
    @Override
    public <V> void sendToKafka(String topic, List<V> values) throws Exception {
        values.stream().forEach(e-> {
            try {
                kafkaTemplate.send(topic, e).get();
            } catch (InterruptedException interruptedException) {
                log.error("Kafka Message push interrupted:{}",e);
            } catch (ExecutionException executionException) {
                log.error("Execution exception in kafka message push:{}",e);
            }
        });
    }
}
