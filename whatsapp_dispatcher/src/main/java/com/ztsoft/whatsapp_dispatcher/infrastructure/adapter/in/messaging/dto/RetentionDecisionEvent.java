package com.ztsoft.whatsapp_dispatcher.infrastructure.adapter.in.messaging.dto;

public record RetentionDecisionEvent(String customerId, Double riskScore, String reasoning, String suggestedAction) {
}
