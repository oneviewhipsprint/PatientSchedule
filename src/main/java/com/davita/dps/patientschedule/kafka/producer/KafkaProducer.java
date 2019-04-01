package com.davita.dps.patientschedule.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {
    //private static final String TOPIC = "patient_alert";
    private static final String TOPIC = "patient_schedule";

    @Autowired
    // Intellij says: "Could not autowire. No beans of KafkaTemplate..."
    // But everything compiles and runs fine!
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        log.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }
}
