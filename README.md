# Trabalho.Guilherme
# Sistema de Aluguel de Quadra

Este projeto representa um sistema para gerenciamento de aluguel de quadras, desenvolvido utilizando Java e o padrão MVC.

---

## 🗄️ Tabelas Identificadas

Foram identificadas as seguintes tabelas (models):

### Cliente
- id
- nome
- telefone
- email

### Horario
- id
- horaInicio
- horaFim
- valor
- disponivel

### Aluguel
- id
- cliente
- horario
- data
- valorCobrado
- pago

---

## ⚙️ Regras Identificadas

- O nome e telefone do cliente são obrigatórios.
- O valor do horário não pode ser negativo.
- Um horário só pode ser alugado se estiver disponível.
- Ao realizar um aluguel, o horário fica indisponível.
- A data do aluguel é obrigatória.
- O pagamento inicia como pendente e pode ser atualizado para pago.

---

## 🧱 Estrutura MVC

O projeto utiliza o padrão MVC com os seguintes pacotes:

- model → Representa os dados do sistema.
- controller → Controla as ações do sistema.
- view → Interface com o usuário.
- repository → Simula o armazenamento de dados.
- main/application → Inicializa o sistema.

- 
