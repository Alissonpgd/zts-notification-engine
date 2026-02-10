package com.ztsoft.aegis_analyzer.domain.port.out;

import com.ztsoft.aegis_analyzer.domain.model.CustomerBehavior;
import com.ztsoft.aegis_analyzer.domain.model.RetentionDecision;
import reactor.core.publisher.Mono;

public interface AIEnginePort {
    Mono<RetentionDecision> send (CustomerBehavior customerBehavior);
}
