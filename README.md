# MvcToHexagonal

Este projeto é um exemplo de aplicação Kotlin + Spring Boot utilizando a arquitetura hexagonal (Ports & Adapters), com foco em boas práticas de separação de responsabilidades, testabilidade e flexibilidade.

## Funcionalidade

Gerenciamento de saudações personalizadas (Greeting), permitindo criar, listar, buscar e remover saudações via API REST.

## Arquitetura

- **Domínio**: regras de negócio, entidades e portas (interfaces)
  - `domain/model`: entidades do domínio
  - `domain/port`: portas inbound (casos de uso) e outbound (repositórios)
  - `domain/service`: implementação dos casos de uso
- **Adapters**:
  - `adapters/inbound`: entrada (REST Controller, DTOs, Mapper)
  - `adapters/outbound`: saída (repositório em memória)
- **Application**: configuração de injeção de dependências
- **Testes**: unidade (domínio) e integração (controller)

## Tecnologias
- Kotlin
- Spring Boot
- MapStruct (conversão DTO <-> domínio)
- JUnit 5

## Como rodar

1. Certifique-se de ter Java 21+ e Maven instalados.
2. Execute:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Acesse a API em: `http://localhost:8080/greetings`

### Exemplos de uso
- `POST /greetings` com JSON `{ "name": "João", "message": "Olá!" }`
- `GET /greetings` para listar
- `GET /greetings/{id}` para buscar por id
- `DELETE /greetings/{id}` para remover

## Testes

Execute todos os testes com:
```bash
./mvnw test
```

## Sobre arquitetura hexagonal

- **Portas inbound**: interfaces que expõem os casos de uso do domínio (`GreetingServicePort`)
- **Portas outbound**: interfaces que abstraem recursos externos (`GreetingRepositoryPort`)
- **Adaptadores**: implementam as portas para REST, persistência, etc.

O domínio não depende de frameworks, apenas de interfaces. Adaptadores conectam o domínio ao mundo externo.

---

Sinta-se à vontade para evoluir este projeto, trocar o repositório em memória por JPA, adicionar novos adaptadores ou expandir os casos de uso!

