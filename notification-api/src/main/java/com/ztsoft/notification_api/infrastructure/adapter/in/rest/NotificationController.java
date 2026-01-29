package com.ztsoft.notification_api.infrastructure.adapter.in.rest;

import com.ztsoft.notification_api.domain.port.in.SendNotificationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final SendNotificationUseCase sendNotificationUseCase;

    @PostMapping
    public Mono<ResponseEntity<Void>> sendNotification(@RequestBody NotificationRequest request) {
        return sendNotificationUseCase.sendNotification(
                request.countryCode(),
                request.phoneNumber(),
                request.message())
                .map(notification -> ResponseEntity.status(HttpStatus.CREATED)
                .<Void>build());
    }
}
