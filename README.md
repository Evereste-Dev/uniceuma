# uniceuma

API REST de cadastro de usuários (CRUD) feita com **Spring Boot 4** e **PostgreSQL**, usada nas aulas da UNICEUMA.

## Estrutura do projeto

```
backend/
├── docker-compose.yml          # banco PostgreSQL em container
└── src/main/java/br/com/uniceuma/backend/
    ├── controller/             # recebe as requisições HTTP (rotas da API)
    ├── service/                # regras de negócio
    ├── repository/             # acesso ao banco de dados (JPA)
    ├── model/                  # entidades (tabelas)
    ├── dto/                    # dados que entram e saem da API
    └── exception/              # tratamento de erros
```

## O que você precisa instalar

- [Git](https://git-scm.com/downloads)
- [JDK 21](https://adoptium.net/) (Java 21 ou superior)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- Um editor: [VS Code](https://code.visualstudio.com/) com o *Extension Pack for Java*, ou IntelliJ IDEA

## Como rodar

**1. Baixe o projeto**

```bash
git clone https://github.com/Eneylton/uniceuma.git
cd uniceuma/backend
```

**2. Suba o banco de dados** (com o Docker Desktop aberto)

```bash
docker compose up -d
```

Isso cria um PostgreSQL na porta **5434**, com banco `db_uniceuma`, usuário `uniceuma` e senha `uniceuma`.

**3. Rode a aplicação**

```bash
# Linux / macOS / Git Bash
./mvnw spring-boot:run

# Windows (PowerShell ou CMD)
mvnw.cmd spring-boot:run
```

A API fica disponível em **http://localhost:8081**. As tabelas são criadas automaticamente no banco.

## Rotas da API

| Método | Rota             | O que faz                |
|--------|------------------|--------------------------|
| POST   | `/usuarios`      | Cadastra um usuário      |
| GET    | `/usuarios`      | Lista todos os usuários  |
| GET    | `/usuarios/{id}` | Busca um usuário pelo id |
| PUT    | `/usuarios/{id}` | Atualiza um usuário      |
| DELETE | `/usuarios/{id}` | Remove um usuário        |

Exemplo de JSON para cadastrar ou atualizar:

```json
{
  "nome": "Maria Silva",
  "email": "maria@email.com",
  "senha": "123456"
}
```

Regras: `nome` e `email` são obrigatórios, o `email` precisa ser válido e a `senha` precisa ter no mínimo 6 caracteres.

Para testar, use [Postman](https://www.postman.com/downloads/), [Insomnia](https://insomnia.rest/download) ou a extensão *REST Client* do VS Code.

## Problemas comuns

- **Erro de conexão com o banco:** confira se o Docker Desktop está aberto e se o container está rodando (`docker ps`).
- **Porta 8081 ou 5434 em uso:** feche o programa que está usando a porta ou mude a porta em `backend/src/main/resources/application.properties` (e no `docker-compose.yml`, no caso do banco).
- **Erro de versão do Java:** rode `java -version` e confirme que é a 21 ou superior.

## Parar o banco

```bash
docker compose down
```
