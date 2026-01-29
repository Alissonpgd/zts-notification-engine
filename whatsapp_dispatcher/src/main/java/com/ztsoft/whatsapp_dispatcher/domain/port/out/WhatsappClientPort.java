package com.ztsoft.whatsapp_dispatcher.domain.port.out;

import com.ztsoft.whatsapp_dispatcher.domain.model.WhatsappMessage;
import reactor.core.publisher.Mono;

public interface WhatsappClientPort {

    Mono<Void> sendMessage(WhatsappMessage message);
}
