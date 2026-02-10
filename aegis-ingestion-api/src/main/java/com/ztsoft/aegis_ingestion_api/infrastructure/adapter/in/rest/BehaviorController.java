package com.ztsoft.aegis_ingestion_api.infrastructure.adapter.in.rest;

import com.ztsoft.aegis_ingestion_api.domain.model.ActionType;
import com.ztsoft.aegis_ingestion_api.domain.model.CustomerBehavior;
import com.ztsoft.aegis_ingestion_api.domain.port.in.CaptureBehaviorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/behavior")
public class BehaviorController {

    private final CaptureBehaviorUseCase captureBehaviorUseCase;

    @PostMapping
    Mono<ResponseEntity<Void>> listen (@RequestBody BehaviorRequest request){
        var behavior = new CustomerBehavior(request.customerId(), ActionType.valueOf(request.action()),request.metadata());
        return captureBehaviorUseCase.capture(behavior).map(
                n -> ResponseEntity.accepted()
                        .build()
        );
    }
}
