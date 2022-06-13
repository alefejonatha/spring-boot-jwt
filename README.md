# Spring Boot JWT

Esta API permite criar, editar, listar, pesquisar e deletar usuarios. O objetivo do projeto é ilustrar a implementação do padrão JSON Web Token.

## Requisitos

* Java 11 ou superior
* Maven 3.8.1 ou superior
* JWT for the JVM versão 0.9.1
  
## Dependências
Há várias dependências de terceiros usadas no projeto. Abra o arquivo pom.xml do Maven para obter detalhes das bibliotecas e versões utilizadas.

## Construindo o Projeto

Clone o projeto: 
```
https://github.com/alefejonatha/spring-boot-jwt.git
```

Acesse o diretório do projeto:
```
cd spring-boot-jwt
```

Use o Maven para construir o servidor:
```
$ mvn spring-boot:run
```
Método | URL | Ação
:---: | --- | ---
POST | localhost:8080/api/v1/users | Salva um usuário
POST | localhost:8080/api/v1/login | Faz o login do usuário e retorna uma JWT token
GET | localhost:8080/api/v1/user/{id} | Procura um usuário utilizando o id
GET | localhost:8080/api/v1/login | Demonstra um endpoint sem restrição de acesso
GET | localhost:8080/api/v1/login/user | Demonstra um endpoint com permissão de acesso apenas a ROLE_USER
GET | localhost:8080/api/v1/books/admin | Demonstra um endpoint com permissão de acesso apenas a ROLE_ADMIN

Importante lembrar que para acessar as rotas restritas você deve inserir o token gerado no login no header da requisição utilizando como KEY "Authorization".

Você pode obter mais informações através da documentação da própria API acessando o link abaixo:
```
http://localhost:8080/swagger-ui/index.html
```
