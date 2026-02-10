package com.ztsoft.aegis_ingestion_api.domain.model;

import java.time.LocalDateTime;

public class CustomerBehavior {

    private String customerId;
    private ActionType action;
    private LocalDateTime timeStamp;
    private String metadata;

    public CustomerBehavior(String customerId, ActionType action, LocalDateTime timeStamp, String metadata) {
        this.customerId = customerId;
        this.action = action;
        this.timeStamp = timeStamp;
        this.metadata = metadata;
        this.validate();
    }

    public CustomerBehavior(String customerId, ActionType action, String metadata) {
        this.customerId = customerId;
        this.action = action;
        this.metadata = metadata;
        this.timeStamp = LocalDateTime.now();
        this.validate();
    }

    public void validate() {
        if(this.customerId == null || this.customerId.isEmpty()){
            throw new IllegalArgumentException("the User ID is required");
        }
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
}
