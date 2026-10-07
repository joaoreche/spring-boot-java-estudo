# 📦 API Produto — RESTful API com Spring Boot & PostgreSQL

> API RESTful desenvolvida em **Java 21** e **Spring Boot** para gerenciamento de catálogo de produtos, construída com foco em boas práticas de arquitetura de software, padrão DTO, mapeamento de objetos e tratamento centralizado de exceções.

---

## 🛠️ Tecnologias Utilizadas

- **Java 21** (LTS)
- **Spring Boot 4.x**
  - **Spring Web MVC** (Exposição de endpoints REST)
  - **Spring Data JPA** (Persistência e comunicação com banco de dados)
  - **Spring Boot Actuator** (Métricas e monitoramento da aplicação)
- **PostgreSQL** (Banco de dados relacional)
- **ModelMapper (3.2.6)** (Mapeamento entre entidades e DTOs)
- **Maven** (Gerenciamento de dependências e build)

---

## 🏛️ Destaques de Arquitetura e Boas Práticas

- **Arquitetura em Camadas (Layered Architecture):**
  - `view/controller`: Recepção de requisições HTTP e entrega de DTOs de resposta.
  - `services`: Regras de negócio e orquestração de dados.
  - `repository`: Comunicação com o banco via `JpaRepository`.
- **Desacoplamento de Domínio (Padrão DTO):**
  - `ProdutoRequest`: Dados de entrada enviados pelo cliente (sem `id`).
  - `ProdutoResponse`: Dados retornados pela API formatados para apresentação.
  - `ProdutoDTO`: Objeto compartilhado para comunicação entre Controller e Service.
- **Injeção de Dependências por Construtor:**
  - Todas as dependências declaradas como `private final`, garantindo imutabilidade e boas práticas recomendadas pela documentação do Spring.
- **Gerenciamento de Beans Customizados:**
  - `ModelMapper` configurado como Bean singleton via `@Configuration` (`AppConfig`), otimizando o consumo de memória e performance.
- **Tratamento Global de Exceções:**
  - `@RestControllerAdvice` capturando exceções como `ResourceNotFoundException` e padronizando as respostas de erro HTTP (ex: 404 Not Found) em JSON através da classe `ErrorMessage`.
- **Segurança de Credenciais e Perfis (Spring Profiles):**
  - Separação entre `application.properties` (configuração base) e `application-local.properties` (credenciais locais ignoradas no Git).

---

## 📁 Estrutura de Pacotes

```text
src/main/java/com/joaoreche/api_produto/
├── config/
│   └── AppConfig.java                 # Configuração do Bean ModelMapper
├── exception/
│   └── ResourceNotFoundException.java # Exceção de recurso não encontrado
├── handler/
│   └── RestExceptionHandler.java      # ControllerAdvice para tratamento global de erros
├── model/
│   ├── entity/
│   │   └── Produto.java               # Entidade JPA mapeada no PostgreSQL
│   └── error/
│       └── ErrorMessage.java          # Modelo padronizado de erro JSON
├── repository/
│   └── ProdutoRepository.java         # Interface Spring Data JPA
├── services/
│   └── ProdutoService.java            # Camada de negócio
├── shared/
│   └── ProdutoDTO.java                # DTO de transferência interna
├── view/
│   ├── controller/
│   │   └── ProdutoController.java     # Endpoints REST da API
│   └── model/
│       ├── ProdutoRequest.java        # Payload de entrada (POST/PUT)
│       └── ProdutoResponse.java       # Payload de saída
└── ApiProdutoApplication.java         # Classe principal do Spring Boot
```

---

## 🔌 Endpoints da API

**Base URL:** `http://localhost:8080/api/produtos`

| Método | Endpoint | Descrição | Status Sucesso |
|---|---|---|---|
| `GET` | `/api/produtos` | Lista todos os produtos cadastrados | `200 OK` |
| `GET` | `/api/produtos/{id}` | Busca um produto específico pelo ID | `200 OK` |
| `POST` | `/api/produtos` | Cadastra um novo produto | `201 Created` |
| `PUT` | `/api/produtos/{id}` | Atualiza os dados de um produto existente | `200 OK` |
| `DELETE` | `/api/produtos/{id}` | Remove um produto do banco de dados | `204 No Content` |

---

### Exemplos de Requisições e Respostas

#### 1. Cadastrar Produto (`POST /api/produtos`)
**Payload de Envio (Request Body):**
```json
{
  "nome": "Mouse Gamer RGB",
  "quantidade": 10,
  "valor": 150.00,
  "observacao": "Sensor óptico 16000 DPI"
}
```

**Resposta (`201 Created`):**
```json
{
  "id": 1,
  "nome": "Mouse Gamer RGB",
  "quantidade": 10,
  "valor": 150.00,
  "observacao": "Sensor óptico 16000 DPI"
}
```

---

#### 2. Buscar por ID Não Existente (`GET /api/produtos/999`)
**Resposta de Erro (`404 Not Found`):**
```json
{
  "titulo": "Recurso não encontrado",
  "mensagem": "Produto com id:999 não foi encontrado",
  "status": 404
}
```

---

## 🚀 Como Executar o Projeto Localmente

### 1. Pré-requisitos
- [JDK 21](https://www.oracle.com/java/technologies/downloads/#java21) instalado e configurado no `PATH`.
- [PostgreSQL](https://www.postgresql.org/download/) instalado e rodando.
- [Git](https://git-scm.com/) instalado.

---

### 2. Clonar o Repositório
```bash
git clone <url-do-repositorio>
cd api-produto
```

---

### 3. Configurar o Banco de Dados
Acesse o terminal do PostgreSQL (`psql` ou via pgAdmin) e crie o banco de dados da aplicação:
```sql
CREATE DATABASE apiproduto;
```

---

### 4. Configurar as Credenciais Locais
Para proteger senhas pessoais, o arquivo com credenciais locais é ignorado no Git.

1. Na pasta `src/main/resources/`, copie o arquivo de exemplo:
   ```bash
   cp src/main/resources/application-local.properties.example src/main/resources/application-local.properties
   ```
2. Abra o arquivo `application-local.properties` e preencha com o usuário e senha do seu PostgreSQL:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/apiproduto
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.datasource.driver-class-name=org.postgresql.Driver
   ```

---

### 5. Executar a Aplicação

#### No Windows (PowerShell / CMD):
```powershell
.\mvnw.cmd spring-boot:run
```

#### No Linux / macOS:
```bash
./mvnw spring-boot:run
```

A aplicação iniciará em: **`http://localhost:8080`**.

---

## 🧪 Testando os Endpoints

Você pode utilizar ferramentas como **Postman**, **Insomnia** ou executar via **cURL**:

```bash
# Listar todos os produtos
curl -X GET http://localhost:8080/api/produtos

# Criar um produto
curl -X POST http://localhost:8080/api/produtos \
  -H "Content-Type: application/json" \
  -d '{"nome":"Teclado Mecânico","quantidade":5,"valor":350.00,"observacao":"Switch Blue"}'
```

---

## 👤 Autor

Desenvolvido por **João Reche**.  
Sinta-se à vontade para se conectar:
- **GitHub:** [joaoreche](https://github.com/joaoreche)
- **LinkedIn:** [João Reche](https://linkedin.com/in/joao-reche)
