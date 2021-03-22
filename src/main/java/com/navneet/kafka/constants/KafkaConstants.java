package com.navneet.kafka.constants;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author navneetprabhakar
 * This class contains all Kafka related Properties
 */
@Configuration
@ConfigurationProperties(prefix = "kafka")
@PropertySources(value = {@PropertySource("classpath:application.properties")})
@Getter
@Setter
public class KafkaConstants {

    /**
     * Server Address
     */
    private String bootstrapAddress;
    /**
     * Server poll timeout
     */
    private Integer pollTimeout;
    /**
     * Offset reset config
     */
    private String autoOffsetResetConfig;
    /**
     * Max poll records
     */
    private Integer maxPollRecordsConfig;
    /**
     * Number of concurrent thread/messages. This depends on the number of partition in kafka topic
     */
    private Integer concurrency;
    /**
     * Topic name
     */
    private String topic;
}
