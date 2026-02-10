package com.ztsoft.whatsapp_dispatcher.infrastructure.adapter.in.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ztsoft.whatsapp_dispatcher.domain.model.WhatsappMessage;
import com.ztsoft.whatsapp_dispatcher.domain.port.in.ProcessMessageUseCase;
import com.ztsoft.whatsapp_dispatcher.domain.records.PhoneNumber;
import com.ztsoft.whatsapp_dispatcher.infrastructure.adapter.in.messaging.dto.NotificationEvent;

import com.ztsoft.whatsapp_dispatcher.infrastructure.adapter.in.messaging.dto.RetentionDecisionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class WhatsappKafkaListener {

    private final ProcessMessageUseCase processMessageUseCase;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "notification-events")
    public Mono<Void> consume(NotificationEvent notificationEvent) {
        var domainPhone =  new PhoneNumber(notificationEvent.getPhoneNumber().countryCode(), notificationEvent.getPhoneNumber().number());
        var message = new WhatsappMessage(notificationEvent.getId(),domainPhone, notificationEvent.getContent());
        return processMessageUseCase.execute(message);
    }

    @KafkaListener(topics = "retention-decisions")
    public void consumeRetentionDecision(String payload) {
        try{
            var event = objectMapper.readValue(payload, RetentionDecisionEvent.class);
            var phone = new PhoneNumber("+55", "83981235295");
            var whatsappMsg = new WhatsappMessage(event.customerId(),phone, event.suggestedAction());
            processMessageUseCase.execute(whatsappMsg).subscribe();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
