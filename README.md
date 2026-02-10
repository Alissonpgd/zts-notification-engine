🛡️ ZTS Notification Engine & AegisFlow AI

🎯 A Missão (O que o sistema faz)

Este repositório contém um ecossistema de microserviços de alta performance focado em comunicação resiliente e retenção inteligente de clientes.
O projeto evoluiu de um simples sistema de mensagens para o AegisFlow: uma engine que utiliza Inteligência Artificial Generativa para analisar o comportamento do usuário em tempo real e decidir a melhor estratégia de engajamento via WhatsApp.

🏗️ O Ecossistema (Os 4 Distritos)
aegis-ingestion-api (O Sensor): Captura eventos de comportamento (ex: tentativas de cancelamento), persiste no MongoDB e despacha para o Kafka.
aegis-analyzer (O Cérebro AI): Ouve os comportamentos, consulta a IA Local (Ollama) para calcular o risco de churn e publica uma decisão estratégica.
notification-api (O Gateway): Porta de entrada para notificações manuais ou de outros sistemas legados.
whatsapp-dispatcher (O Braço/Soldado): O worker final. Ouve tanto as notificações simples quanto as decisões da IA e executa o envio (simulado via logs profissionais).

🏗️ Arquitetura e Tecnologias (O Blueprint)
Java 21 & Spring WebFlux: O estado da arte em programação reativa para IO não-bloqueante.
Arquitetura Hexagonal: Separação rigorosa entre Domain (Regras), Application (Gerência) e Infrastructure (Ferramentas).
Apache Kafka: O amortecedor de eventos que garante que nenhuma mensagem seja perdida.
Spring AI + Ollama: Integração com LLMs (Llama3/Phi-3/TinyLlama) rodando localmente.
MongoDB Reactive: Armazenamento assíncrono para auditoria e histórico.

🛠️ Pré-requisitos (O Checklist)

1. Infraestrutura Terrestre
   Podman ou Docker: Para rodar Kafka, Zookeeper e MongoDB.
   Ollama: Para rodar a IA no seu PC. Download aqui.

2. Preparando a IA (Terminal)
   Abra o terminal e baixe os modelos necessários:
   code
   Bash
   ollama pull phi3       # Recomendado para equilíbrio entre inteligência e velocidade
   ollama pull tinyllama  # Recomendado para computadores com pouca RAM

🚦 Ritual de Execução

1. Iniciar a Base
   Na raiz do projeto (zts-notification-engine), execute:
   code
   Bash
   podman compose up -d
   Kafdrop (Visualizar mensagens): http://localhost:9000
   MongoDB Compass: mongodb://localhost:27017

2. Iniciar os Microserviços (Ordem recomendada)
   whatsapp-dispatcher (Porta 8081)
   aegis-analyzer (Porta 8084)
   aegis-ingestion-api (Porta 8080)
   notification-api (Porta 8082)
   🧪 Testando o Fluxo Inteligente (A Prova Real)
   Mande um comportamento de risco para o MS Ingestion:
   POST http://localhost:8080/v1/behavior
   code
   JSON
   {
   "customerId": "CLIENTE-VIP-99",
   "action": "CANCEL_ATTEMPT",
   "metadata": "Tentou cancelar 3 vezes na tela de planos"
   }
   🧐 O que acontece depois?
   O Ingestion salva no Mongo e joga no Kafka.
   O Analyzer recebe, pergunta ao Ollama: "Qual o risco?".
   A IA responde um JSON. O Analyzer carimba o ID e joga a decisão no Kafka.
   O Dispatcher lê a decisão e grita no console:
   🚨 [AI DECISION] ENVIANDO WHATSAPP ESTRATÉGICO: [Sugestão da IA]
   🧠 Notas de Engenharia (Massa Muscular Mental)
   Resiliência Reativa: Implementamos subscribeOn(Schedulers.boundedElastic()) para que a lentidão da IA não congele o consumo do Kafka.
   Programação Defensiva: O sistema possui um motor de "Cirurgia de Strings" (indexOf/substring) para limpar respostas ruidosas de IAs pequenas e um Plano B (Fallback) caso o JSON falhe.
   Desacoplamento de Elite: Microserviços conversam via String JSON + ObjectMapper manual, permitindo que cada serviço tenha sua própria versão da classe de dados sem quebrar o rádio.
   
"Um sistema que não apenas transporta dados, mas pensa sobre eles." 🚀🏆