package com.ztsoft.aegis_analyzer.infrastructure.adapter.in.messaging;

import com.ztsoft.aegis_analyzer.domain.model.CustomerBehavior;
import com.ztsoft.aegis_analyzer.domain.port.in.AnalyzeRetentionUseCase;
import com.ztsoft.aegis_analyzer.infrastructure.adapter.in.dto.CustomerBehaviorEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BehaviorConsumer {

    private final AnalyzeRetentionUseCase analyzeRetentionUseCase;

    @KafkaListener(topics = "customer-behavior-topic")
    public void consume (CustomerBehaviorEvent customerBehaviorEvent) {
        var behavior = new CustomerBehavior(
                customerBehaviorEvent.customerId(),
                customerBehaviorEvent.action(),
                customerBehaviorEvent.metadata()
        );
        analyzeRetentionUseCase.analyze(behavior).subscribe();
    }
}
