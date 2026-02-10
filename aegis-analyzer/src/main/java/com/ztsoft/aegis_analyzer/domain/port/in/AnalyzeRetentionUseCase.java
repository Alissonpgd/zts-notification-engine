package com.ztsoft.aegis_analyzer.domain.port.in;

import com.ztsoft.aegis_analyzer.domain.model.CustomerBehavior;
import com.ztsoft.aegis_analyzer.domain.model.RetentionDecision;
import reactor.core.publisher.Mono;

public interface AnalyzeRetentionUseCase {

    Mono<RetentionDecision> analyze (CustomerBehavior customerBehavior);
}
