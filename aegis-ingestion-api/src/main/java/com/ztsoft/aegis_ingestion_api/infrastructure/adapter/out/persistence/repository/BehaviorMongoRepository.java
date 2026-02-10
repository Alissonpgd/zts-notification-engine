package com.ztsoft.aegis_ingestion_api.infrastructure.adapter.out.persistence.repository;

import com.ztsoft.aegis_ingestion_api.infrastructure.adapter.out.persistence.entity.CustomerBehaviorEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BehaviorMongoRepository extends ReactiveMongoRepository<CustomerBehaviorEntity, String> {

}
