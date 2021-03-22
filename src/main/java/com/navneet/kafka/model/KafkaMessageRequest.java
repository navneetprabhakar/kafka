package com.navneet.kafka.model;

import com.navneet.kafka.constants.FlowType;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class KafkaMessageRequest {
    private String name;
    private FlowType stage;
    private Integer value;
}
