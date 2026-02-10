package com.ztsoft.aegis_ingestion_api.infrastructure.adapter.out.persistence.transform;

import com.ztsoft.aegis_ingestion_api.domain.model.CustomerBehavior;
import com.ztsoft.aegis_ingestion_api.infrastructure.adapter.out.persistence.entity.CustomerBehaviorEntity;
import org.springframework.stereotype.Component;

@Component
public class BehaviorPersistenceMapper {
    public CustomerBehaviorEntity toEntity(CustomerBehavior customerBehavior) {
        return new CustomerBehaviorEntity(
                customerBehavior.getCustomerId(),
                customerBehavior.getAction(),
                customerBehavior.getTimeStamp(),
                customerBehavior.getMetadata()
        );
    }

    public CustomerBehavior toDomain(CustomerBehaviorEntity customerBehaviorEntity) {
        return new CustomerBehavior(
                customerBehaviorEntity.getCustomerId(),
                customerBehaviorEntity.getAction(),
                customerBehaviorEntity.getTimeStamp(),
                customerBehaviorEntity.getMetadata()
        );
    }

}
