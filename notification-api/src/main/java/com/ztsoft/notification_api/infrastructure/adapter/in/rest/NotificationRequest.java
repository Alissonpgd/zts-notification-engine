package com.ztsoft.notification_api.infrastructure.adapter.in.rest;

public record NotificationRequest(String countryCode,String phoneNumber ,String message) {
}
