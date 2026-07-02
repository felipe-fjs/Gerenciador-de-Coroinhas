# Gerenciador de Coroinhas

API REST desenvolvida com **Java 21** e **Spring Boot** para gerenciamento de coroinhas em comunidades paroquiais. O projeto utiliza autenticação JWT, controle de acesso por perfis e persistência de dados com MySQL.

## Funcionalidades

* Autenticação com JWT
* Controle de acesso por perfis (`ROLE_COORDENADOR` e `ROLE_ARTICULADOR`)
* Gerenciamento de comunidades
* Cadastro e gerenciamento de coroinhas
* Gerenciamento de usuários

## Tecnologias

* Java 21
* Spring Boot 3
* Spring Security
* JWT
* Spring Data JPA / Hibernate
* MySQL
* Maven

## Como executar

### Pré-requisitos

* Java 21+
* Maven 3.9+ (ou Maven Wrapper)
* MySQL 8+

### Clonar o projeto

```bash
git clone https://github.com/felipe-fjs/Gerenciador-de-Coroinhas.git
cd Gerenciador-de-Coroinhas
```

### Configurar o banco de dados

Edite o arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gerenciador_coroinhas
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### Executar a aplicação

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em:

```
http://localhost:8080
```

## Principais Endpoints

| Método | Endpoint                                 | Descrição            |
| ------ | ---------------------------------------- | -------------------- |
| POST   | `/auth/login`                            | Realiza autenticação |
| POST   | `/auth/registro`                         | Cadastra um usuário  |
| GET    | `/comunidades/todos`                     | Lista as comunidades |
| POST   | `/comunidades/novo`                      | Cria uma comunidade  |

## Status do Projeto

🚧 Em desenvolvimento. Novas funcionalidades e melhorias serão adicionadas continuamente.

## Licença

Este projeto foi desenvolvido para fins de estudo e portfólio.
