package com.ztsoft.notification_api.infrastructure.adapter.out.messaging;

import com.ztsoft.notification_api.domain.model.Notification;
import com.ztsoft.notification_api.domain.port.out.NotificationEventPort;
import com.ztsoft.notification_api.infrastructure.adapter.out.messaging.dto.NotificationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class NotificationEventAdapter implements NotificationEventPort {

    private final ReactiveKafkaProducerTemplate<String, NotificationEvent> kafkaProducerTemplate;

    @Override
    public Mono<Void> sendNotificationEvent(Notification notification) {
        String topic = "notification-events";
        NotificationEvent notificationEvent = notificationEvent(notification);
        return kafkaProducerTemplate.send(topic, notificationEvent.getId(), notificationEvent)
                .then();
    }

    private NotificationEvent notificationEvent(Notification notification) {
        return new NotificationEvent(
                notification.getId().toString(),
                notification.getDestination(),
                notification.getContent(),
                notification.getStatus(),
                notification.getCreatedAt()
        );
    }
}