package com.ztsoft.notification_api.infrastructure.adapter.out.persistence;

import com.ztsoft.notification_api.domain.model.Notification;
import com.ztsoft.notification_api.domain.port.out.NotificationRepositoryPort;
import com.ztsoft.notification_api.infrastructure.adapter.out.persistence.repository.NotificationMongoRepository;
import com.ztsoft.notification_api.infrastructure.adapter.out.persistence.transform.NotificationPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class NotificationPersistenceAdapter implements NotificationRepositoryPort {

    private final NotificationMongoRepository notificationMongoRepository;
    private final NotificationPersistenceMapper mapper;

    @Override
    public Mono<Notification> saveNotification(Notification notification) {
        return notificationMongoRepository.save(mapper.toEntity(notification))
                .map(mapper::toDomain);
    }

}
