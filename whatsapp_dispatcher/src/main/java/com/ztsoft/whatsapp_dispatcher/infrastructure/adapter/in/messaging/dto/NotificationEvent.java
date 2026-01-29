package com.ztsoft.whatsapp_dispatcher.infrastructure.adapter.in.messaging.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import java.time.LocalDateTime;
@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEvent {

    @JsonProperty("id")
    private String id;
    @JsonProperty("phone_number")
    private PhoneNumber phoneNumber;
    @JsonProperty("content")
    private String content;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;


    public NotificationEvent(String id, PhoneNumber destination, String content, Status status, LocalDateTime createdAt) {
        this.id = id;
        this.phoneNumber = destination;
        this.content = content;
        this.createdAt = createdAt;
    }

}
