package com.ztsoft.aegis_analyzer.domain.port.out;

import com.ztsoft.aegis_analyzer.domain.model.RetentionDecision;
import reactor.core.publisher.Mono;

public interface DecisionBrokerPort {
    Mono<Void> execute (RetentionDecision retentionDecision);
}
