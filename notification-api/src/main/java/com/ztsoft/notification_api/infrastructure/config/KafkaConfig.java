package com.ztsoft.notification_api.infrastructure.config;

import com.ztsoft.notification_api.infrastructure.adapter.out.messaging.dto.NotificationEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    // Pega o endereço do application.yml (vamos configurar isso depois)
    // Se não tiver lá, usa localhost:9092 como padrão
    @Value("${spring.kafka.bootstrap-servers:localhost:9092}")
    private String bootstrapServers;

    @Bean
    public ReactiveKafkaProducerTemplate<String, NotificationEvent> reactiveKafkaProducerTemplate() {
        Map<String, Object> props = new HashMap<>();

        // 1. Onde está o Kafka?
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // 2. A Chave da mensagem é String (UUID)
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // 3. O Valor da mensagem é um JSON (NotificationEvent)
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // Cria as opções do Sender baseadas nessas propriedades
        SenderOptions<String, NotificationEvent> options = SenderOptions.create(props);

        // Retorna o Template pronto para ser injetado no Adaptador
        return new ReactiveKafkaProducerTemplate<>(options);
    }
}