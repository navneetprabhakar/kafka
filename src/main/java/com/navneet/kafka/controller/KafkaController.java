package com.navneet.kafka.controller;

import com.navneet.kafka.model.KafkaMessageRequest;
import com.navneet.kafka.model.KafkaMessageResponse;
import com.navneet.kafka.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author navneetprabhakar
 */
@RestController
@RequestMapping("v1")
public class KafkaController {

    @Autowired
    private KafkaService kafkaService;

    /**
     * This API pushes message to kafka topic
     * @param message
     * @return
     */
    @PostMapping("push")
    public KafkaMessageResponse pushToKafka(@RequestBody KafkaMessageRequest message){
        kafkaService.pushMessageToKafka(message);
        return KafkaMessageResponse.builder().status("success").message("pushed to topic").build();
    }
}
