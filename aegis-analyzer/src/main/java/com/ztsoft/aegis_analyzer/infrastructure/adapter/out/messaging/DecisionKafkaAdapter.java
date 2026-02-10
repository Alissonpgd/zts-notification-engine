package com.ztsoft.aegis_analyzer.infrastructure.adapter.out.messaging;

import com.ztsoft.aegis_analyzer.domain.model.RetentionDecision;
import com.ztsoft.aegis_analyzer.domain.port.out.DecisionBrokerPort;
import com.ztsoft.aegis_analyzer.infrastructure.adapter.out.messaging.dto.RetentionDecisionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DecisionKafkaAdapter implements DecisionBrokerPort {
    private final ReactiveKafkaProducerTemplate<String, RetentionDecisionEvent> kafkaTemplate;

    @Override
    public Mono<Void> execute (RetentionDecision retentionDecision) {
        String topic = "retention-decisions";
        RetentionDecisionEvent retentionDecisionEvent = toEvent(retentionDecision);
        return kafkaTemplate.send(topic, retentionDecisionEvent.customerId(), retentionDecisionEvent)
                .then();
    }

    private RetentionDecisionEvent toEvent(RetentionDecision retentionDecision) {
        return new RetentionDecisionEvent(
                retentionDecision.getCustomerId(),
                retentionDecision.getRiskScore(),
                retentionDecision.getReasoning(),
                retentionDecision.getSuggestedAction()
        );
    }
}
