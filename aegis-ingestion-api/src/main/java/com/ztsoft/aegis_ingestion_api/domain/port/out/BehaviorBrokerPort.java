package com.ztsoft.aegis_ingestion_api.domain.port.out;

import com.ztsoft.aegis_ingestion_api.domain.model.CustomerBehavior;
import reactor.core.publisher.Mono;

public interface BehaviorBrokerPort {

    Mono<Void> send(CustomerBehavior behavior);
}
