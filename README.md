# 🚀 Notification System - Architecture Hexagonal & Kafka

## 🎯 A Missão (O que o sistema faz)
Este sistema é um motor de notificações de alta performance, projetado para ser resiliente e escalável (Visão Elon Musk). Ele permite que qualquer sistema da empresa peça o envio de uma mensagem sem precisar esperar o processo terminar.

O sistema é dividido em dois distritos independentes:
1.  **Notification API (MS1):** Recebe pedidos via HTTP e os protege no banco e no rádio (Kafka).
2.  **WhatsApp Dispatcher (MS2):** Ouve o rádio e entrega as mensagens no mundo real.

---

## 🏗️ Arquitetura (O Blueprint)

Usamos a **Arquitetura Hexagonal** para manter o "Coração" (Regras de Negócio) isolado da "Rua" (Ferramentas).

**Fluxo de Dados:**
`Cliente` -> `MS1 (Controller)` -> `MS1 (Service)` -> `Kafka` -> `MS2 (Listener)` -> `MS2 (Service)` -> `Console/WhatsApp`

---

## 🛠️ Ferramentas Necessárias (O Checklist)

Para ligar este motor, você precisa de:
*   **Java 21**: O motor mais moderno do mercado.
*   **Podman / Docker**: Para criar a cidade (Infraestrutura).
*   **Maven**: O gerente de bibliotecas.
*   **Postman / Insomnia**: Para simular o cliente.

---

## 🚦 Como Rodar (O Ritual de Inicialização)

### 1. Suba a Infraestrutura
Na raiz do projeto, onde está o arquivo `compose.yaml`, use o terminal:

```bash
podman compose up -d

Acesse http://localhost:9000 para ver se o Kafka ligou.

### 2. Ligue a Notification API (MS1)
Porta: 8080

Vá na pasta notification-api e rode a classe Application.

###3. Ligue o WhatsApp Dispatcher (MS2)
Porta: 8081
Vá na pasta whatsapp-dispatcher e rode a classe Application.

🧪 Testando o Sistema (A Prova Real)

Mande um POST para http://localhost:8080/v1/notifications com este corpo:
code
JSON
{
  "countryCode": "+55",
  "phoneNumber": "11999999999",
  "message": "Mensagem de mestre enviada com sucesso!"
}

📂 Estrutura de Pastas (O Palácio da Memória)

Para cada microsserviço, seguimos este padrão:

domain: O Cofre (Entidades, Records, Enums e Interfaces/Ports).
application: O Gerente (Services que orquestram a lógica).
infrastructure: A Rua (Adapters para Kafka, Mongo e Web).
shared: Utilitários comuns.
