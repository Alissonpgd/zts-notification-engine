package com.ztsoft.aegis_ingestion_api.infrastructure.adapter.out.persistence;

import com.ztsoft.aegis_ingestion_api.domain.model.CustomerBehavior;
import com.ztsoft.aegis_ingestion_api.domain.port.out.BehaviorRepositoryPort;
import com.ztsoft.aegis_ingestion_api.infrastructure.adapter.out.persistence.repository.BehaviorMongoRepository;
import com.ztsoft.aegis_ingestion_api.infrastructure.adapter.out.persistence.transform.BehaviorPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BehaviorPersistenceAdapter implements BehaviorRepositoryPort {

    private final BehaviorMongoRepository behaviorMongoRepository;
    private final BehaviorPersistenceMapper mapper;


    @Override
    public Mono<CustomerBehavior> save(CustomerBehavior behavior) {
        return behaviorMongoRepository.save(mapper.toEntity(behavior))
                .map(mapper::toDomain);
    }
}
