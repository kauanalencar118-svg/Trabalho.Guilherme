# Sistema de Controle de Aluguel de Quadra Esportiva

## Tabelas Identificadas

### 1. Cliente
Armazena os dados dos clientes que alugam a quadra.

| Campo       | Tipo    | Restrições              |
|-------------|---------|-------------------------|
| id          | int     | PK, auto-incremento     |
| nome        | String  | NOT NULL, não vazio     |
| telefone    | String  | obrigatório             |
| email       | String  | opcional                |
| created_at  | Date    | data de cadastro        |

### 2. Horario
Representa os blocos de tempo disponíveis para aluguel da quadra.

| Campo       | Tipo    | Restrições                   |
|-------------|---------|------------------------------|
| id          | int     | PK, auto-incremento          |
| horaInicio  | String  | ex: "08:00"                  |
| horaFim     | String  | ex: "09:00"                  |
| valor       | double  | NOT NULL, maior ou igual a 0 |
| disponivel  | boolean | true por padrão              |

### 3. Aluguel
Registra a reserva de um horário por um cliente.

| Campo        | Tipo    | Restrições                        |
|--------------|---------|-----------------------------------|
| id           | int     | PK, auto-incremento               |
| cliente      | Cliente | FK, NOT NULL                      |
| horario      | Horario | FK, NOT NULL                      |
| data         | String  | data do aluguel (ex: "2024-06-10")|
| valorCobrado | double  | calculado automaticamente         |
| pago         | boolean | false por padrão                  |
| created_at   | Date    | data do registro                  |

---

## Regras de Negócio Identificadas

### Regras de Validação (entrada de dados)
1. **Nome do cliente não pode ser vazio** – ao cadastrar um cliente, o campo `nome` é obrigatório e não pode conter apenas espaços em branco.
2. **Telefone do cliente é obrigatório** – ao cadastrar um cliente, o campo `telefone` deve ser preenchido.
3. **Valor do horário não pode ser negativo** – ao cadastrar um horário, o campo `valor` deve ser maior ou igual a zero.
4. **Hora de início e fim obrigatórias** – um horário deve sempre ter `horaInicio` e `horaFim` definidos.

### Regras de Integridade das Reservas
5. **Não é permitido reservar um horário já ocupado** – antes de registrar um aluguel, o sistema verifica se o horário está disponível (`disponivel == true`). Se não estiver, lança uma exceção e impede a reserva.
6. **Ao registrar um aluguel, o horário é marcado como indisponível** – após uma reserva bem-sucedida, `horario.disponivel` é alterado para `false`.
7. **Um cliente pode alugar mais de um horário no mesmo dia** – o sistema deve permitir múltiplas reservas do mesmo cliente em datas iguais.

### Regras de Cálculo
8. **Valor cobrado é calculado automaticamente** – o `valorCobrado` do aluguel é preenchido com o `valor` definido no horário no momento da reserva.
9. **Total do dia é calculado sob demanda** – o sistema calcula a soma dos valores cobrados em todos os aluguéis de um mesmo cliente em uma mesma data, somando os `valorCobrado` dos registros filtrados.

### Regras de Consulta
10. **Consulta de aluguéis por data** – o sistema permite listar todos os aluguéis registrados em um determinado dia, filtrando pelo campo `data`.
11. **Controle de pagamento** – o campo `pago` indica se o valor foi recebido, permitindo distinguir clientes que já pagaram dos que ainda não pagaram.

---

## Estrutura dos Projetos

```
projeto1/           # Classes simples sem padrão MVC
  src/
    Cliente.java
    Horario.java
    Aluguel.java
    SistemaQuadra.java
    Main.java

projeto2/           # Refatorado com padrão MVC
  src/
    model/
      Cliente.java
      Horario.java
      Aluguel.java
    repository/
      ClienteRepository.java
      HorarioRepository.java
      AluguelRepository.java
    controller/
      ClienteController.java
      HorarioController.java
      AluguelController.java
    view/
      QuadraView.java
    Main.java
```
