# E-Bank microservices

The Maven reactor contains five independent Spring Boot applications:

| Service | Port | Purpose |
| --- | ---: | --- |
| `discovery-service` | 8761 | Eureka service registry |
| `customer-service` | 8066 | Customer REST API and MCP tools |
| `ebank-service` | 8067 | Bank account REST API and MCP tools |
| `gateway-service` | 9999 | Gateway with Eureka-based routes |
| `ebank-bot` | 8070 | Chatbot using the customer and account MCP tools |

## Build and test

Run the complete reactor from the repository root:

```powershell
mvn test
```

## Run the platform

Start each command in a separate terminal, from the repository root, in this
order:

```powershell
mvn -pl discovery-service spring-boot:run
mvn -pl customer-service spring-boot:run
mvn -pl ebank-service spring-boot:run
mvn -pl gateway-service spring-boot:run
```

The chatbot uses Groq's OpenAI-compatible API, which offers a free developer
tier. Create a personal API key in the Groq console, then set it in the
PowerShell session before starting the bot:

```powershell
$env:GROQ_API_KEY = "your-groq-api-key"
mvn -pl ebank-bot spring-boot:run
```

The key is read from the environment and must not be committed to source
control. Override `AI_MODEL`, `AI_BASE_URL`, `CUSTOMER_MCP_URL`, or
`ACCOUNT_MCP_URL` in the environment when using a different compatible model
provider or service address.

Chat endpoint: `GET http://localhost:8070/chat?query=Bonjour`

Gateway routes are discovered from Eureka and are available under
`http://localhost:9999/{service-id}/...` (for example,
`http://localhost:9999/CUSTOMER-SERVICE/customers`).
