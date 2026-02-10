package com.ztsoft.aegis_analyzer.application.service;

import com.ztsoft.aegis_analyzer.domain.model.CustomerBehavior;
import com.ztsoft.aegis_analyzer.domain.model.RetentionDecision;
import com.ztsoft.aegis_analyzer.domain.port.in.AnalyzeRetentionUseCase;
import com.ztsoft.aegis_analyzer.domain.port.out.AIEnginePort;
import com.ztsoft.aegis_analyzer.domain.port.out.DecisionBrokerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class RetentionService implements AnalyzeRetentionUseCase {

    private final AIEnginePort aiEnginePort;
    private final DecisionBrokerPort decisionBrokerPort;

    @Override
    public Mono<RetentionDecision> analyze(CustomerBehavior customerBehavior) {
        return aiEnginePort.send(customerBehavior)
                .flatMap(Ia -> decisionBrokerPort.execute(Ia)
                        .thenReturn(Ia));
    }
}
