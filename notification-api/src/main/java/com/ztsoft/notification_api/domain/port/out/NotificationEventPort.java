package com.ztsoft.notification_api.domain.port.out;

import com.ztsoft.notification_api.domain.model.Notification;
import reactor.core.publisher.Mono;

public interface NotificationEventPort {
    Mono<Void> sendNotificationEvent(Notification notification);
}
