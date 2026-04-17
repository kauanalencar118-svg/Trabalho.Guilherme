# Planejamento de Sistemas - Exercícios 1, 2 e 3

---

## Exercício 1 – Controle de Aluguel de Quadra Esportiva

### Tabelas

| Tabela | Campos |
|---|---|
| **clientes** | id, nome, telefone |
| **horarios** | id, horario_inicio, horario_fim, valor |
| **alugueis** | id, cliente_id, horario_id, data, valor_cobrado |

### Regras
- Nome do cliente não pode ser vazio
- Valor do horário não pode ser negativo
- Não é permitido reservar um horário já ocupado na mesma data
- O sistema calcula automaticamente o valor total quando o cliente aluga mais de um horário no mesmo dia
- Deve ser possível consultar todos os aluguéis por data

---

## Exercício 2 – Controle de Biblioteca Escolar

### Tabelas

| Tabela | Campos |
|---|---|
| **livros** | id, titulo, autor, quantidade_disponivel |
| **alunos** | id, nome |
| **emprestimos** | id, livro_id, aluno_id, data_emprestimo, data_devolucao |

### Regras
- Título do livro não pode ser vazio
- Quantidade disponível não pode ser negativa
- Não é permitido emprestar um livro com quantidade disponível igual a zero
- Ao registrar empréstimo, a quantidade disponível do livro diminui em 1
- Ao registrar devolução, a quantidade disponível aumenta em 1
- Deve ser possível consultar livros atualmente emprestados e alunos com empréstimos em aberto

---

## Exercício 3 – Controle de Pedidos em Lanchonete

### Tabelas

| Tabela | Campos |
|---|---|
| **produtos** | id, nome, descricao, preco |
| **pedidos** | id, data, valor_total |
| **itens_pedido** | id, pedido_id, produto_id, quantidade |

### Regras
- Nome do produto não pode ser vazio
- Preço do produto não pode ser negativo
- Um pedido só pode ser finalizado se tiver pelo menos um produto adicionado
- O valor total do pedido é calculado automaticamente com base nos produtos e quantidades
- Deve ser possível consultar pedidos por data e o total faturado no período
