package com.ztsoft.aegis_analyzer.infrastructure.adapter.out.messaging.dto;

public record RetentionDecisionEvent(String customerId, Double riskScore, String reasoning, String suggestedAction) {
}
