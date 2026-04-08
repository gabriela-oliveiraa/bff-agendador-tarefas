# 🔀 BFF Agendador de Tarefas — Backend For Frontend

Serviço **BFF (Backend for Frontend)** da plataforma **Agendador de Tarefas**. Atua como ponto de entrada único para o cliente (frontend/mobile), orquestrando chamadas aos microsserviços internos (`usuario`, `agendador-tarefas`, `notificacao`) via **OpenFeign**. Expõe uma API REST documentada com **Swagger/OpenAPI**.

---

## 🚀 Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 21 |
| Spring Boot | 4.0.3 |
| Spring Web MVC | — |
| Spring Cloud OpenFeign | 2025.1.0 |
| Feign HTTP Client 5 (Apache HC5) | 13.9.3 |
| SpringDoc OpenAPI (Swagger) | 3.0.2 |
| Lombok | — |
| Docker / Docker Compose | — |
| Maven | — |

---

## 📁 Estrutura do Projeto

```
bff-agendador-tarefas/
├── .github/workflows/        # Pipelines CI/CD (GitHub Actions)
├── .mvn/wrapper/             # Wrapper do Maven
├── src/
│   ├── main/
│   │   ├── java/             # Controllers, Feign Clients, DTOs, configurações
│   │   └── resources/        # application.properties e configurações
│   └── test/                 # Testes unitários e de integração
├── Dockerfile                # Imagem Docker da aplicação
├── docker-compose.yml        # Orquestração dos serviços
├── pom.xml                   # Dependências e configurações Maven
└── mvnw / mvnw.cmd           # Maven Wrapper
```

---

## ⚙️ Configuração e Execução

### Pré-requisitos

- Java 21+
- Maven (ou use o wrapper `./mvnw`)
- Docker e Docker Compose
- Microsserviços `usuario`, `agendador-tarefas` e `notificacao` em execução

### Executando com Docker Compose

```bash
# Clone o repositório
git clone https://github.com/gabriela-oliveiraa/bff-agendador-tarefas.git
cd bff-agendador-tarefas

# Suba os serviços
docker-compose up --build
```

### Executando localmente

```bash
# Build do projeto
./mvnw clean install

# Execução
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

---

## 📖 Documentação da API

Com a aplicação rodando, acesse a documentação interativa (Swagger UI):

```
http://localhost:8080/swagger-ui.html
```

O Swagger lista todos os endpoints disponíveis, parâmetros esperados e modelos de resposta.

---

## 🏗️ Arquitetura — Padrão BFF

O BFF centraliza e simplifica a comunicação entre o cliente e os microsserviços:

```
                    ┌─────────────────────────┐
    Cliente  ──────▶│   bff-agendador-tarefas │
(Frontend/Mobile)   └────────────┬────────────┘
                                 │ OpenFeign
              ┌──────────────────┼──────────────────┐
              ▼                  ▼                   ▼
         ┌─────────┐    ┌──────────────────┐   ┌────────────┐
         │ usuario │    │agendador-tarefas │   │notificacao │
         └─────────┘    └──────────────────┘   └────────────┘
         PostgreSQL            MongoDB              SMTP
```

**Benefícios do padrão BFF:**
- Reduz o número de chamadas do cliente (aggregation)
- Adapta os dados ao formato necessário pelo frontend
- Centraliza autenticação e regras de negócio de apresentação
- Facilita o versionamento de APIs por tipo de cliente

---

## 🔗 Feign Clients

Os clientes Feign abstraem as chamadas HTTP aos microsserviços internos. Exemplo:

```java
@FeignClient(name = "usuario", url = "${services.usuario.url}")
public interface UsuarioClient {
    @GetMapping("/usuarios/{id}")
    UsuarioDTO buscarUsuario(@PathVariable Long id);
}
```

---

## 🧪 Testes

```bash
./mvnw test
```

---

## 📄 Licença

Este projeto foi desenvolvido para fins de estudo e prática com microsserviços em Java/Spring Boot.
