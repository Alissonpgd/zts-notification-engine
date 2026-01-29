package com.ztsoft.notification_api.domain.model;

import com.ztsoft.notification_api.domain.enums.Status;
import com.ztsoft.notification_api.domain.records.PhoneNumber;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Notification {

    private UUID id;
    private PhoneNumber destination;
    private String content;
    private Status status;
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PhoneNumber getDestination() {
        return destination;
    }

    public void setDestination(PhoneNumber destination) {
        this.destination = destination;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Notification() {
    }

    public Notification(UUID id, PhoneNumber destination, String content, Status status, LocalDateTime createdAt) {
        this.id = id;
        this.destination = destination;
        this.content = content;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Notification(PhoneNumber destination, String content) {
        this.id = UUID.randomUUID();
        this.destination = destination;
        this.content = content;
        this.status = Status.PENDING;
        this.createdAt = LocalDateTime.now();

        this.validateContent();
        this.validatePhoneNumber();
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", destination=" + destination +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }

    public String validateContent() {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty.");
        } if (content.length() > 160) {
            throw new IllegalArgumentException("Content exceeds the maximum length of 160 characters.");
        }
        return content;
    }

    public String validatePhoneNumber() {
        Objects.requireNonNull(destination, "Destination cannot be null.");
        Objects.requireNonNull(destination.number(), "Phone number cannot be null.");
        Objects.requireNonNull(destination.countryCode(), "Country code cannot be null.");

        validateNumberFormat(destination.number());
        validateCountryCodeFormat(destination.countryCode());

        return content;
    }
    private void validateNumberFormat(String number) {
        if (number.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty.");
        }
        if (!number.matches("\\d{10,15}")) {
            throw new IllegalArgumentException("Phone number must be between 10 to 15 digits.");
        }
    }

    private void validateCountryCodeFormat(String countryCode) {
        if (countryCode.isEmpty()) {
            throw new IllegalArgumentException("Country code cannot be empty.");
        }
        if (!countryCode.matches("\\+\\d{1,3}")) {
            throw new IllegalArgumentException("Country code must start with '+' followed by 1 to 3 digits.");
        }
    }

}
