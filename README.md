# C.A.V-enterprise - SatGuard API 🚀

Projeto da disciplina de **Java Advanced** para a Global Solution.

A API SatGuard é o módulo responsável pelo monitoramento e gerenciamento da economia espacial, rastreando satélites, órbitas, empresas responsáveis, plataformas e detritos espaciais, emitindo alertas automatizados. O repositório reflete uma arquitetura perfeitamente acoplada e idêntica aos serviços C# .NET da equipe.

## 🔗 Links Importantes

- **Vídeo de Demonstração Técnica (Java):** [Assistir no YouTube](https://youtu.be/0kMx0qqI8mU)
- **Vídeo Pitch:** [Assistir no YouTube](https://youtu.be/WOXdy4cQ1gE)
- **Link do Deploy da Aplicação:** [SatGuard API - Deploy](https://c-a-v-enterprise.onrender.com)
- **Documentação da API (Swagger/OpenAPI):** [Swagger UI Deploy](https://c-a-v-enterprise.onrender.com/swagger-ui.html) (ou acesse `http://localhost:8080/swagger-ui.html` rodando localmente)

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.0**
- **Spring Data JPA** (com Hibernate)
- **Spring Security + JWT (Auth0)** para Autenticação e Autorização
- **Spring HATEOAS** para navegação RESTful
- **Spring Validation** para regras de validação nos DTOs
- **Oracle Database** (via ojdbc11)
- **Swagger / OpenAPI 3** para documentação interativa
- **Lombok** para ganho de produtividade

## ⚙️ Instruções de Execução

1. Clone o repositório na sua máquina:
   ```bash
   git clone https://github.com/gabriel-g-dev/C.A.V-enterprise.git
   ```
2. Altere as credenciais do banco de dados no arquivo `src/main/resources/application.properties` para a sua string de conexão Oracle ou H2 de testes.
3. Certifique-se de que a variável de ambiente secreta do JWT (`api.security.token.secret`) esteja definida (por padrão, usa-se fallback para testes).
4. Execute o projeto usando o Maven Wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
5. Acesse a documentação pelo navegador: `http://localhost:8080/swagger-ui.html`

### 🔑 Como Testar a API (Autenticação JWT)
A API é protegida pelo Spring Security. Como o projeto está configurado para utilizar o banco de dados H2 (em memória) para facilitar a avaliação local, o banco inicia vazio. Para testar:
1. Abra o Swagger e encontre a rota `POST /auth/register`.
2. Registre um usuário administrador inicial com o JSON:
   ```json
   {
     "nome": "Admin",
     "email": "admin@satguard.com",
     "senha": "123456",
     "tipo": "ADMIN"
   }
   ```
3. Agora, vá na rota `POST /auth/login` e faça o login com o email e senha criados.
4. Copie a string do `token` que será retornada.
5. Vá até o topo da página do Swagger, clique no botão **Authorize**, cole apenas o token copiado na caixinha e clique em Authorize.
6. Pronto! Agora você pode testar as rotas de criação (POST, PUT, DELETE) livremente.

## 📚 Informações Relevantes para Avaliação

Para atingir a nota máxima nos critérios exigidos da GS:
- **Herança Relacional Real (Table-Per-Type / JOINED):** A classe `ObjetoEspacial` utiliza `@Inheritance(strategy = InheritanceType.JOINED)`, fazendo com que `Satelite` e `DetritoEspacial` herdem a tabela no banco usando `@PrimaryKeyJoinColumn`.
- **Value Objects / Embedded:** A entidade `Empresa` utiliza a classe embutida `@Embedded Endereco`, e a entidade `Plataforma` utiliza a classe `@Embedded Coordenada`.
- **Chave Composta:** A entidade de auditoria `AcessoLog` utiliza uma chave composta baseada em `@EmbeddedId` (`AcessoLogId`).
- **Múltiplas Tabelas:** 7 tabelas base conectadas através de relacionamentos `@ManyToOne`.
- **Boas Práticas e Arquitetura:** Controllers com resposta paginada (`Pageable`), DTOs utilizando `Records` do Java 14+, e retorno `EntityModel` provendo links HATEOAS.
- **Tratamento de Exceções:** Implementado globalmente através da classe `GlobalExceptionHandler` utilizando `@ControllerAdvice`.
- **Testes Unitários (QA):** Implementados utilizando JUnit 5 e Mockito (ex: `SateliteServiceTest`) para validação isolada das regras de negócio.

## 👥 Integrantes

- **André Bellandi Vital Rodrigues** - RM: 564662
- **Vitor Augusto Oliveira de Abreu** - RM: 564227
- **Gabriel Garcia Mayo Delatore** - RM: 563298
