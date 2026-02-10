package com.ztsoft.aegis_ingestion_api.infrastructure.adapter.out.messaging.dto;

import com.ztsoft.aegis_ingestion_api.domain.model.ActionType;

import java.time.LocalDateTime;

public record CustomerBehaviorEvent(
        String customerId,
        ActionType action,
        LocalDateTime timeStamp,
        String metadata) {
}
