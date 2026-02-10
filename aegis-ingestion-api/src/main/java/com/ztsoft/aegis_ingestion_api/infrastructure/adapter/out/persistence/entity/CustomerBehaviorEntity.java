package com.ztsoft.aegis_ingestion_api.infrastructure.adapter.out.persistence.entity;

import com.ztsoft.aegis_ingestion_api.domain.model.ActionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "behavior")
public class CustomerBehaviorEntity {

    @Id
    private String customerId;
    private ActionType action;
    private LocalDateTime timeStamp;
    private String metadata;
}
