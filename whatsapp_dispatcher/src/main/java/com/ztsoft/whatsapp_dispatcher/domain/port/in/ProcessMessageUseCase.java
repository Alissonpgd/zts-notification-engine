package com.ztsoft.whatsapp_dispatcher.domain.port.in;

import com.ztsoft.whatsapp_dispatcher.domain.model.WhatsappMessage;
import reactor.core.publisher.Mono;

public interface ProcessMessageUseCase {

    Mono<Void> execute (WhatsappMessage message);
}
