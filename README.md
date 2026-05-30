# C.A.V-enterprise - SatGuard API 🚀

Projeto da disciplina de **Java Advanced** para a Global Solution.

A API SatGuard é o módulo responsável pelo monitoramento e gerenciamento da economia espacial, rastreando satélites, órbitas, empresas responsáveis, plataformas e detritos espaciais, emitindo alertas automatizados. O repositório reflete uma arquitetura perfeitamente acoplada e idêntica aos serviços C# .NET da equipe.

## 🔗 Links Importantes

- **Link do Vídeo de Apresentação (Pitch):** [Apresentação YouTube](https://youtube.com/exemplo-video-pitch)
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

## 📚 Informações Relevantes para Avaliação

Para atingir a nota máxima nos critérios exigidos da GS:
- **Herança (MappedSuperclass):** A classe `Satelite` e `DetritoEspacial` herdam características da classe abstrata `ObjetoEspacial` (Herança no código Java).
- **Embedded:** A entidade `Plataforma` utiliza a classe embutida `@Embedded Coordenada`.
- **Chave Composta:** A entidade de auditoria `AcessoLog` utiliza uma chave composta baseada em `@EmbeddedId` (`AcessoLogId`).
- **Múltiplas Tabelas:** 7 tabelas base conectadas através de relacionamentos `@ManyToOne`.
- **Boas Práticas e Arquitetura:** Controllers com resposta paginada (`Pageable`), DTOs utilizando `Records` do Java 14+, e retorno `EntityModel` provendo links HATEOAS.
- **Tratamento de Exceções:** Implementado globalmente através da classe `GlobalExceptionHandler` utilizando `@ControllerAdvice`.
