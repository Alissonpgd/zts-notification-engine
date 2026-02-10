package com.ztsoft.aegis_analyzer.infrastructure.adapter.out.ia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ztsoft.aegis_analyzer.domain.model.CustomerBehavior;
import com.ztsoft.aegis_analyzer.domain.model.RetentionDecision;
import com.ztsoft.aegis_analyzer.domain.port.out.AIEnginePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@RequiredArgsConstructor
@Component
public class OllamaAIAdapter implements AIEnginePort {

    private final ChatClient chatClient;
    private final ObjectMapper objectMapper; // Nosso dicionário para ler JSON manual

    @Override
    public Mono<RetentionDecision> send(CustomerBehavior customerBehavior) {
        log.info(">>> IA ANALISANDO COMPORTAMENTO DO CLIENTE: {}", customerBehavior.getId());

        // Prompt simples, direto e compatível com TinyOllama
        var prompt = """
    Analise o comportamento do cliente e retorne APENAS um JSON válido.

    Regras:
    - Só responda em Português
    - Não explique nada
    - Não escreva texto fora do JSON
    - riskScore deve estar entre 0.0 e 10.0

    Entrada:
    ClienteId: %s
    Ação: %s

    Formato do JSON de saída:
    {
      "riskScore": number,
      "reasoning": "string",
      "suggestedAction": "string"
    }
    """.formatted(
                customerBehavior.getId(),
                customerBehavior.getAction()
        );

        return Mono.fromCallable(() ->
                        chatClient
                                .prompt(prompt)
                                .call()
                                .content()
                )
                .subscribeOn(Schedulers.boundedElastic())
                .map(respostaIa -> {
                    try {
                        log.info(">>> IA RESPONDEU (BRUTO): {}", respostaIa);

                        if (respostaIa == null || !respostaIa.contains("{")) {
                            log.warn(">>> IA NÃO RETORNOU JSON. USANDO FALLBACK.");
                            return new RetentionDecision(
                                    customerBehavior.getId(),
                                    5.0,
                                    "IA não retornou JSON válido",
                                    "REVISÃO MANUAL"
                            );
                        }

                        int inicio = respostaIa.indexOf("{");
                        int fim = respostaIa.lastIndexOf("}");

                        if (inicio == -1 || fim == -1 || fim <= inicio) {
                            throw new IllegalArgumentException("JSON malformado retornado pela IA");
                        }

                        String jsonLimpo = respostaIa.substring(inicio, fim + 1);

                        RetentionDecision decision =
                                objectMapper.readValue(jsonLimpo, RetentionDecision.class);

                        decision.setCustomerId(customerBehavior.getId());
                        return decision;

                    } catch (Exception e) {
                        log.error(">>> ERRO AO PROCESSAR RESPOSTA DA IA", e);

                        return new RetentionDecision(
                                customerBehavior.getId(),
                                1.0,
                                "Erro técnico ao processar resposta da IA",
                                "LOGAR E REVISAR"
                        );
                    }
                });
    }

}
