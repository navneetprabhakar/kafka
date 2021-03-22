# Kafka Producer & Consumer Implementation using Spring Boot
This repository is implementation of Kafka Producer & Consumer with Spring Boot Application with REST API to push message to topic

# Introduction:
Apache Kafka is a framework implementation of a software bus using stream-processing. It is an open-source software platform developed by the Apache Software Foundation written in Scala and Java. The project aims to provide a unified, high-throughput, low-latency platform for handling real-time data feeds. Kafka can connect to external systems (for data import/export) via Kafka Connect and provides Kafka Streams, a Java stream processing library. Kafka uses a binary TCP-based protocol that is optimized for efficiency and relies on a "message set" abstraction that naturally groups messages together to reduce the overhead of the network roundtrip.


# Approach
1. Add maven dependecies for kafka
2. Create a Kafka Producer to push message in Kafka Topic
3. Create a Kafka Consumer to read message from Kafka Topic
4. Create REST API to push message 

# API Description: 
- API: Add message to topic
- METHOD: POST
- URL: http://localhost:8080/kafka/v1/push
- Body: This is a Sample Request to push message

```json
{
    "name":"Navneet",
    "stage":"STAGE2",
    "value":2
}
```
## Steps to setup Kafka 
https://kafka.apache.org/quickstart

