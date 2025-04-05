# Sistema de Pedidos de Delivery

## Conceito

O **Sistema de Pedidos de Delivery** é uma aplicação web onde o **usuário (cliente)** pode realizar pedidos e simular pagamentos. O **proprietário (admin)** pode gerenciar seus produtos por meio de um sistema CRUD, sendo **notificado por e-mail** sempre que um pedido é realizado.

### Tecnologias utilizadas:
- Spring Boot (Java)
- PostgreSQL
- Spring Security + JWT
- Envio de e-mail
- API REST
- React (Frontend via Vite)

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
- **Pedidos**: Relaciona os usuários aos produtos comprados.
- **Pagamentos**:
  - Validação do número do cartão
  - Reconhecimento do tipo de cartão (Visa, Mastercard, Amex, Hipercard)
  - Armazena número, CVV, validade
- **E-mails**:
  - Envio automático de mensagem padrão ao admin ao receber um pedido

---

## Banco de Dados

A conexão com o banco é configurada no `application.properties`.

Exemplo de configuração:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/delivery
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

Há também o arquivo exampleapplication.properties com um exemplo pronto para uso.

## CRUD - (Admin / Proprietário)
O proprietário tem acesso total ao gerenciamento dos produtos:

Funcionalidades:
- Criar novos produtos
- Atualizar nome, descrição e valor
- Listar todos os produtos
- Deletar produto por ID

## Integração com o Frontend
Foi configurado **CORS** para permitir a comunicação entre backend e frontend (React com Vite).

```java
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:5173")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}

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

Exemplo de uso em controller:

```java
public ResponseEntity<?> ValidateCard(@RequestBody CardDTO card){
    if (!ValidateCard.validatenumberCard(card.getNumberCard())) {
        return ResponseEntity.badRequest().body(new ErrorResponse("Invalid card number!"));
    }
    return ResponseEntity.ok(card);
}