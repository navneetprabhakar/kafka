package com.navneet.kafka.service.impl;

import com.navneet.kafka.constants.KafkaConstants;
import com.navneet.kafka.model.KafkaMessageRequest;
import com.navneet.kafka.producer.KafkaProducer;
import com.navneet.kafka.service.KafkaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author navneetprabhakar
 */
@Service
@Log4j2
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaConstants kafkaConstants;
    /**
     * This method pushes the message object to Kafka Topic
     *
     * @param message
     */
    @Override
    public void pushMessageToKafka(KafkaMessageRequest message) {
        log.info("Pushing message:{} , to topic:{}", message.toString(), kafkaConstants.getTopic());
        try{
            kafkaProducer.sendToKafka(kafkaConstants.getTopic(), message);
        }catch(Exception e){
            log.error("An error occurred in pushing message to topic:{}",e);
        }
    }

    /**
     * This method processes the received message
     * @param message
     */
    @Override
    public void processReceivedMessage(KafkaMessageRequest message) {
        log.info("Received message for processing:{}", message.toString());
        switch (message.getStage()){
            case STAGE1:
                log.info("Message is for Stage 1, name:{} , value:{}", message.getName(), message.getValue());
                break;
            case STAGE2:
                log.info("Message is for Stage 2, name:{} , value:{}", message.getName(), message.getValue());
                break;
            case STAGE3:
                log.info("Message is for Stage 3, name:{} , value:{}", message.getName(), message.getValue());
                break;
            default:
                log.error("Invalid Stage received:");
        }
    }
}
