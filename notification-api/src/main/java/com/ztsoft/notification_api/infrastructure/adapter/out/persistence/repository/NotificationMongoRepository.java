package com.ztsoft.notification_api.infrastructure.adapter.out.persistence.repository;

import com.ztsoft.notification_api.infrastructure.adapter.out.persistence.entity.NotificationEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface NotificationMongoRepository extends ReactiveMongoRepository<NotificationEntity, String> {

}
