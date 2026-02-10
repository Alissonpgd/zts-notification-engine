package com.ztsoft.aegis_ingestion_api.domain.port.in;

import com.ztsoft.aegis_ingestion_api.domain.model.CustomerBehavior;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface CaptureBehaviorUseCase {

    Mono<CustomerBehavior> capture(CustomerBehavior behavior);

}
