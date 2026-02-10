package com.ztsoft.whatsapp_dispatcher.infrastructure.adapter.out.client;

import com.ztsoft.whatsapp_dispatcher.domain.model.WhatsappMessage;
import com.ztsoft.whatsapp_dispatcher.domain.port.out.WhatsappClientPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class WhatsappClientAdapter implements WhatsappClientPort {


    @Override
    public Mono<Void> sendMessage(WhatsappMessage message) {
        log.info("🚨 [AI DECISION] ENVIANDO WHATSAPP ESTRATÉGICO: " + message.getContent());
        return Mono.empty();
    }
}
