package com.ztsoft.notification_api.infrastructure.adapter.out.persistence.transform;

import com.ztsoft.notification_api.domain.model.Notification;
import com.ztsoft.notification_api.infrastructure.adapter.out.persistence.entity.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationPersistenceMapper {
    public NotificationEntity toEntity(Notification notification) {
        return new NotificationEntity(
                notification.getId().toString(),
                notification.getDestination(),
                notification.getContent(),
                notification.getStatus(),
                notification.getCreatedAt()
        );
    }

    public Notification toDomain(NotificationEntity entity) {
        return new Notification(
                java.util.UUID.fromString(entity.getId()),
                entity.getDestination(),
                entity.getContent(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}
