package com.ztsoft.notification_api.application.service;

import com.ztsoft.notification_api.domain.model.Notification;
import com.ztsoft.notification_api.domain.port.in.SendNotificationUseCase;
import com.ztsoft.notification_api.domain.port.out.NotificationEventPort;
import com.ztsoft.notification_api.domain.port.out.NotificationRepositoryPort;
import com.ztsoft.notification_api.domain.records.PhoneNumber;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class NotificationService implements SendNotificationUseCase {

    private final NotificationRepositoryPort notificationRepositoryPort;
    private final NotificationEventPort notificationEventPort;




    public NotificationService(NotificationRepositoryPort notificationRepositoryPort, NotificationEventPort notificationEventPort) {
        this.notificationRepositoryPort = notificationRepositoryPort;
        this.notificationEventPort = notificationEventPort;
    }

    @Override
    public Mono<Notification> sendNotification(String countryCode, String number, String content) {

        var phone = new PhoneNumber(countryCode, number);
        Notification notification = new Notification(phone , content);
        return notificationRepositoryPort.saveNotification(notification)
                .flatMap(savedNotification -> notificationEventPort.sendNotificationEvent(savedNotification)
                        .thenReturn(savedNotification));

    }
}
