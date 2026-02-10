package com.ztsoft.aegis_ingestion_api.infrastructure.adapter.in.rest;

public record BehaviorRequest(String customerId, String action, String metadata) {
}
