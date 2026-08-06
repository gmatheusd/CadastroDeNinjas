# 🥷 Cadastro de Ninjas

Um sistema de cadastro no melhor estilo *shinobi*: **Ninjas** e **Missões**, com relacionamento entre eles, API REST documentada via Swagger e uma interface web feita com Thymeleaf. Tudo containerizado e pronto pra rodar com um `docker compose up`.

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-6DB33F?logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-database-4169E1?logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-ready-2496ED?logo=docker&logoColor=white)
![License](https://img.shields.io/badge/license-MIT-green)

## O que tem aqui

- **CRUD completo de Ninjas** — nome, email, idade, rank, imagem e a missão em que estão engajados.
- **CRUD completo de Missões** — nome, dificuldade e os ninjas vinculados a ela.
- **Relacionamento Ninja ↔ Missão** (`ManyToOne` / `OneToMany`), com desvinculação automática ao excluir uma missão que ainda tem ninjas alocados.
- **Interface web** (Thymeleaf) pra listar, cadastrar, editar, ver detalhes e excluir — com tela de confirmação quando a exclusão afeta ninjas vinculados.
- **API REST** documentada com Swagger/OpenAPI.
- **Migrations** com Flyway.
- **Docker Compose** com banco Postgres e a aplicação, prontos pra subir juntos.

## Stack

| Camada         | Tecnologia                          |
|----------------|--------------------------------------|
| Linguagem      | Java 21                              |
| Framework      | Spring Boot 4.1.0                    |
| Persistência   | Spring Data JPA + PostgreSQL         |
| Migrations     | Flyway                               |
| Views          | Thymeleaf                            |
| Documentação   | springdoc-openapi (Swagger UI)       |
| Boilerplate    | Lombok                               |
| Containers     | Docker + Docker Compose              |

## Como rodar

### Pré-requisitos

- Java 21
- Docker e Docker Compose
- (Opcional, pra rodar fora do Docker) Maven — o projeto já traz o wrapper `./mvnw`

### 1. Configure as variáveis de ambiente

Crie um arquivo `.env` na raiz do projeto:

```env
DB_NAME=CadastroDeNinjasDb
DB_USER=seu_usuario
DB_PASSWORD=sua_senha
DB_URL=jdbc:postgresql://localhost:5432/CadastroDeNinjasDb
```

### 2. Suba tudo com Docker Compose

```bash
docker compose up --build
```

Isso sobe o Postgres e a aplicação juntos. A aplicação fica disponível em `http://localhost:8080`.

### 3. (Alternativa) Rodando local, só com o banco no Docker

Se preferir rodar a aplicação direto pela IDE (mais rápido pra desenvolver), suba só o banco:

```bash
docker compose up postgres
```

E rode a aplicação normalmente:

```bash
./mvnw spring-boot:run
```

## Endpoints principais

### Ninjas — `/ninjas`

| Método   | Rota                  | Descrição                  |
|----------|------------------------|-----------------------------|
| `GET`    | `/ninjas/listar`       | Lista todos os ninjas       |
| `GET`    | `/ninjas/listar/{id}`  | Busca um ninja por id       |
| `POST`   | `/ninjas/criar`        | Cadastra um novo ninja      |
| `PUT`    | `/ninjas/alterar/{id}` | Atualiza um ninja           |
| `DELETE` | `/ninjas/deletar/{id}` | Remove um ninja             |

### Missões — `/missoes`

| Método   | Rota                   | Descrição                  |
|----------|-------------------------|-----------------------------|
| `GET`    | `/missoes/listar`       | Lista todas as missões      |
| `GET`    | `/missoes/listar/{id}`  | Busca uma missão por id     |
| `POST`   | `/missoes/criar`        | Cadastra uma nova missão    |
| `PUT`    | `/missoes/alterar/{id}` | Atualiza uma missão         |
| `DELETE` | `/missoes/deletar/{id}` | Remove uma missão           |

### Interface web

| Rota                | Descrição                          |
|----------------------|-------------------------------------|
| `/ninjas/ui/listar`  | Lista de ninjas (tela principal)    |
| `/missoes/ui/listar` | Lista de missões                    |

### Documentação da API

Com a aplicação rodando, a documentação interativa (Swagger UI) fica em:

```
http://localhost:8080/swagger-ui/index.html
```

## Publicando a imagem no Docker Hub

```bash
./mvnw clean package -DskipTests
docker build -t gmatheusd/cadastro-de-ninjas:latest .
docker push gmatheusd/cadastro-de-ninjas:latest
```

## Licença

Distribuído sob a licença MIT. Veja [LICENSE](LICENSE) para mais detalhes.

---

Feito com 🍥 por [M.Douglas](https://github.com/gmatheusd)
