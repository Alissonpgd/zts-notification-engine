package com.ztsoft.notification_api.domain.port.in;

import com.ztsoft.notification_api.domain.model.Notification;
import reactor.core.publisher.Mono;

public interface SendNotificationUseCase {
    Mono<Notification> sendNotification(String countryCode, String number, String content);

}
