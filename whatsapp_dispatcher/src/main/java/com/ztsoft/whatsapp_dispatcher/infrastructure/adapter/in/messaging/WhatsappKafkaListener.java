package com.ztsoft.whatsapp_dispatcher.infrastructure.adapter.in.messaging;

import com.ztsoft.whatsapp_dispatcher.domain.model.WhatsappMessage;
import com.ztsoft.whatsapp_dispatcher.domain.port.in.ProcessMessageUseCase;
import com.ztsoft.whatsapp_dispatcher.domain.records.PhoneNumber;
import com.ztsoft.whatsapp_dispatcher.infrastructure.adapter.in.messaging.dto.NotificationEvent;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class WhatsappKafkaListener {
    private final ProcessMessageUseCase processMessageUseCase;

    public WhatsappKafkaListener(ProcessMessageUseCase processMessageUseCase) {
        this.processMessageUseCase = processMessageUseCase;
    }
    @KafkaListener(topics = "notification-events")
    public Mono<Void> consume(NotificationEvent notificationEvent) {
        var domainPhone =  new PhoneNumber(notificationEvent.getPhoneNumber().countryCode(), notificationEvent.getPhoneNumber().number());
        var message = new WhatsappMessage(notificationEvent.getId(),domainPhone, notificationEvent.getContent());
        return processMessageUseCase.execute(message);
    }
}
