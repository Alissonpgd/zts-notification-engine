package com.ztsoft.aegis_analyzer.infrastructure.adapter.in.dto;

import java.time.LocalDateTime;

public record CustomerBehaviorEvent(
        String customerId,
        String action,
        LocalDateTime timeStamp,
        String metadata) {
}
