package com.ztsoft.notification_api.infrastructure.adapter.out.persistence.entity;

import com.ztsoft.notification_api.domain.enums.Status;
import com.ztsoft.notification_api.domain.records.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notification")
public class NotificationEntity {

    @Id
    private String id;
    private PhoneNumber destination;
    private String content;
    private Status status;
    private LocalDateTime createdAt;


}
