package com.navneet.kafka.consumer;

import com.navneet.kafka.constants.KafkaConstants;
import com.navneet.kafka.model.KafkaMessageRequest;
import com.navneet.kafka.service.KafkaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author navneetprabhakar
 */
@Component
@Log4j2
public class KafkaConsumer {

    @Autowired
    private KafkaConstants kafkaConstants;

    @Autowired
    private KafkaService kafkaService;


    /**
     * Kafka Listener for the topic
     * @param kafkaMessageList
     * @param acknowledgment
     * @param partitions
     * @param topics
     * @param offsets
     */
    @KafkaListener(id = "service-topic-group", topicPattern = "#{kafkaConstants.getTopic()}", containerFactory = "kafkaTopic")
    public void kafkaListener(List<KafkaMessageRequest> kafkaMessageList, Acknowledgment acknowledgment,
                              @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                              @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics, @Header(KafkaHeaders.OFFSET) List<Long> offsets){
        try{
            log.info("Total Messages received:{}", kafkaMessageList.size());
            for(KafkaMessageRequest message: kafkaMessageList){
                log.info("Processing message :{}", message.toString());
                kafkaService.processReceivedMessage(message);
            }
        }catch(Exception e){
            log.error("An error occurred in processing the message:{}",e);
        }finally {
            acknowledgment.acknowledge();
        }
    }
}
