package com.ztsoft.whatsapp_dispatcher.application.service;

import com.ztsoft.whatsapp_dispatcher.domain.model.WhatsappMessage;
import com.ztsoft.whatsapp_dispatcher.domain.port.in.ProcessMessageUseCase;
import com.ztsoft.whatsapp_dispatcher.domain.port.out.WhatsappClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WhatsappService implements ProcessMessageUseCase {

    private final WhatsappClientPort whatsappClientPort;

    @Override
    public Mono<Void> execute(WhatsappMessage message) {
        return whatsappClientPort.sendMessage(message);
    }
}
