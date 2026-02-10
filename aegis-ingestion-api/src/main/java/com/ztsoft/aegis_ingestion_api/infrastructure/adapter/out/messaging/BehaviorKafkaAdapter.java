package com.ztsoft.aegis_ingestion_api.infrastructure.adapter.out.messaging;

import com.ztsoft.aegis_ingestion_api.domain.model.CustomerBehavior;
import com.ztsoft.aegis_ingestion_api.domain.port.out.BehaviorBrokerPort;
import com.ztsoft.aegis_ingestion_api.infrastructure.adapter.out.messaging.dto.CustomerBehaviorEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class BehaviorKafkaAdapter implements BehaviorBrokerPort {

    private final ReactiveKafkaProducerTemplate<String, CustomerBehaviorEvent> kafkaProducerTemplate;

    public CustomerBehaviorEvent toEvent (CustomerBehavior behavior) {
        return new CustomerBehaviorEvent(
                behavior.getCustomerId(),
                behavior.getAction(),
                behavior.getTimeStamp(),
                behavior.getMetadata()
        );
    }
    @Override
    public Mono<Void> send(CustomerBehavior behavior) {
        var event = toEvent(behavior);
        return kafkaProducerTemplate.send(
                "customer-behavior-topic",
                event.customerId(),
                event
        ).then();
    }
}
