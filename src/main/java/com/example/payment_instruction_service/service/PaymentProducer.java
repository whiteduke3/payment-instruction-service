package com.example.payment_instruction_service.service;

import com.example.payment_instruction_service.model.PaymentInstruction;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PaymentProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String topic = "payment.instructions"; // could also inject from props

    public PaymentProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(PaymentInstruction instruction) {
        try {
            // Build payload
            PaymentEvent event = new PaymentEvent(
                    instruction.getId().toString(),
                    instruction.getDebtorAccount(),
                    instruction.getCreditorAccount(),
                    instruction.getAmount(),
                    instruction.getCurrency(),
                    instruction.getReference(),
                    instruction.getCreated()
            );

            String json = objectMapper.writeValueAsString(event);

            kafkaTemplate.send(topic, instruction.getId().toString(), json);
        } catch (Exception e) {
            throw new RuntimeException("Failed to publish payment event to Kafka", e);
        }
    }
}

