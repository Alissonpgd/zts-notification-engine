package com.ztsoft.aegis_ingestion_api.application.service;

import com.ztsoft.aegis_ingestion_api.domain.model.CustomerBehavior;
import com.ztsoft.aegis_ingestion_api.domain.port.in.CaptureBehaviorUseCase;
import com.ztsoft.aegis_ingestion_api.domain.port.out.BehaviorBrokerPort;

import com.ztsoft.aegis_ingestion_api.domain.port.out.BehaviorRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class BehaviorService implements CaptureBehaviorUseCase {

    private final BehaviorBrokerPort behaviorBrokerPort;
    private final BehaviorRepositoryPort behaviorRepositoryPort;

    @Override
    public Mono<CustomerBehavior> capture(CustomerBehavior behavior){
        return behaviorRepositoryPort.save(behavior)
                .flatMap(saveBehavior -> behaviorBrokerPort.send(saveBehavior)
                        .thenReturn(behavior)
                );
    }

}
