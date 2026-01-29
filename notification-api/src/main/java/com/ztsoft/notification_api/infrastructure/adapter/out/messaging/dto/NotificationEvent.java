package com.ztsoft.notification_api.infrastructure.adapter.out.messaging.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ztsoft.notification_api.domain.enums.Status;
import com.ztsoft.notification_api.domain.records.PhoneNumber;
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
