# Sistema de Pedidos de Delivery

## Conceito

O **Sistema de Pedidos de Delivery** é uma aplicação web onde o **usuário (cliente)** pode realizar pedidos e simular pagamentos. O **proprietário (admin)** pode gerenciar seus produtos por meio de um sistema CRUD, sendo **notificado por e-mail** sempre que um pedido é realizado.

### Tecnologias utilizadas:
- Spring Boot (Java)
- PostgreSQL
- Spring Security + JWT
- Envio de e-mail
- API REST
- Thymelaf + Javascript (para o frontend)

---

## Funcionalidades de Autenticação

A autenticação é feita com **Spring Security** e **JWT (JSON Web Token)**, garantindo segurança nas requisições.

### Cadastro

Informações obrigatórias:
- Nome completo (usado para login)
- E-mail
- Senha
- Tipo de usuário (`user` para clientes, `admin` para proprietários)

### Login

Informações obrigatórias:
- Username
- Senha

---

## Armazenamento de Dados

Os dados são organizados e armazenados no **PostgreSQL**, com estrutura pensada para escalabilidade e integridade.

### Entidades principais

- **Usuários**: Armazena dados de clientes e administradores.
- **Home**: Página que exibe todos os produtos disponíveis.
- **Produtos**:
  - Criar novo produto
  - Atualizar nome, valor e descrição
  - Deletar por ID
  - Upload de imagem (armazenada localmente em `resources/images/uploads`)
- **Pagamentos**:
  - Validação do número do cartão
  - Reconhecimento do tipo de cartão (Visa, Mastercard, Amex, Hipercard)
  - Armazena número, CVV, validade
- **E-mails**:
  - Envio automático de mensagem padrão ao admin ao receber um pedido

---

### Armazenamento com Cookies
No controle de acesso à rota inicial (/home), são definidas rotas distintas para usuários com perfil admin e user. Para isso, utiliza-se o armazenamento de cookies contendo a informação da role do usuário.

Ao receber a role via cookie após a autenticação, o sistema realiza o redirecionamento automático para a rota correspondente, conforme o perfil identificado:

- **admin** → redireciona para */home/admin*

- **user** → redireciona para */home/user*

Esse mecanismo garante uma experiência personalizada de acordo com o nível de acesso do usuário e facilita a gestão de permissões no sistema.

## Banco de Dados

A conexão com o banco é configurada no `application.properties`.

Exemplo de configuração:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/delivery
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```
Há também o arquivo exampleapplication.properties com um exemplo pronto para uso.


## CRUD - (Admin / Proprietário)
O proprietário tem acesso total ao gerenciamento dos produtos:

Funcionalidades:
- Criar novos produtos
- Atualizar nome, descrição e valor
- Listar todos os produtos
- Deletar produto por ID

## Testes
As rotas da API foram testadas usando o Insomnia.

## Tratamento de Erros
Classe genérica de erro:
```java
public class ErrorResponse {
    private final String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
```

Exemplo de uso em controller:

```java
public ResponseEntity<?> ValidateCard(@RequestBody CardDTO card){
    if (!ValidateCard.validatenumberCard(card.getNumberCard())) {
        return ResponseEntity.badRequest().body(new ErrorResponse("Invalid card number!"));
    }
    return ResponseEntity.ok(card);
}
```
