# Pet Friends - Sistema de Microsserviços 🐾

## Descrição
O **Pet Friends** é um sistema distribuído simulando o fluxo de e-commerce para uma pet shop. Construído com **Spring Boot** e **Java 21**, o projeto implementa uma arquitetura orientada a eventos, utilizando o **RabbitMQ** para promover comunicação assíncrona e desacoplamento total entre os domínios da aplicação.

O ecossistema é composto por três microsserviços principais:
1. **Pedidos**: Porta de entrada do usuário. Responsável por receber a intenção de compra, salvar o pedido em banco de dados H2 e publicar os eventos de "Pedido Criado" nas exchanges (Fanout) do RabbitMQ.
2. **Almoxarifado**: Consome os eventos para registrar os itens do pedido que precisam ser separados no estoque.
3. **Transporte**: Consome os eventos (trazendo IDs e dimensões do pacote) para gerar os registros de entrega e despacho das mercadorias.

---

## Como Rodar

### Passo a Passo
1. **Subir o RabbitMQ:**
   Na pasta raiz do projeto, acesse a pasta `docker` (onde o `docker-compose.yml` está configurado) e suba o contêiner:
   ```bash
   docker-compose up -d
   ```
   *(Você pode acessar o painel de gerencialmente do RabbitMQ em `http://localhost:15672` com `guest`/`guest`)*

2. **Iniciar os Microsserviços:**
   Abra 3 terminais separados, um para cada microsserviço, e execute-os com o Maven (ou rode diretamente pela sua IDE):
   
   **Pedidos:**
   ```bash
   cd Pedidos
   mvn spring-boot:run
   ```
   
   **Almoxarifado:**
   ```bash
   cd Almoxarifado
   mvn spring-boot:run
   ```
   
   **Transporte:**
   ```bash
   cd Transporte
   mvn spring-boot:run
   ```

---

## Como Testar

Com os 3 microsserviços e o RabbitMQ rodando, você pode testar o fluxo de ponta a ponta realizando uma requisição HTTP `POST`.

### 1. Disparando um Pedido
Use o Postman, Insomnia ou cURL para enviar um POST para o serviço de **Pedidos** (por padrão rodando na porta 8080):

**URL:** `POST http://localhost:8080/pedidos`  
**Headers:** `Content-Type: application/json`

**Body (JSON):**
```json
{
  "nomeCliente": "João Silva",
  "endereco": {
    "cep": "12345-678",
    "numero": 100,
    "rua": "Rua das Flores",
    "bairro": "Centro",
    "complemento": "Apto 201",
    "cidade": "São Paulo",
    "uf": "SP"
  },
  "itens": [
    {
      "productId": 1,
      "quantidade": 2
    }
  ],
  "dimensoes": {
    "altura": 25.5,
    "largura": 15.0,
    "profundidade": 30.0,
    "peso": 2.5
  }
}
```

### 2. Validando o Resultado
- **Retorno da API**: Você deverá receber um `HTTP 201 Created` retornando o ID gerado para o pedido.
- **Logs (Console)**: Olhe os terminais do **Almoxarifado** e do **Transporte**. Você verá os logs de que eles receberam e processaram a mensagem do RabbitMQ com sucesso.
- **Banco de Dados**: Acesse o painel do H2 de cada serviço (ex: `http://localhost:8080/h2-console`) para validar se as entidades (`Pedido`, `Entrega`) foram perfeitamente persistidas em suas respectivas tabelas.
