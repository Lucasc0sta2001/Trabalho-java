# 💻 Sistema de Processamento de Folha de Pagamento

<div align="center">

**Faculdade Estácio de Sá – Juiz de Fora/MG**

**Projeto Acadêmico – Programação em Java**

</div>

---

## 📌 Sobre o projeto

Sistema desenvolvido em Java para simular o processamento de pagamentos de funcionários e faturas, com controle de saldo empresarial, registro de transações e mecanismo de rollback.

O projeto tem como objetivo aplicar conceitos fundamentais da Programação Orientada a Objetos (POO), utilizando interfaces, herança, polimorfismo, exceções personalizadas e estruturas de dados.

## 🎯 Objetivos

- Desenvolver um sistema de processamento de pagamentos.
- Aplicar conceitos de programação orientada a objetos.
- Implementar tratamento de exceções personalizadas.
- Controlar o saldo disponível da empresa.
- Registrar transações realizadas.
- Implementar mecanismo de rollback para desfazer operações em caso de falhas.

## 🛠️ Tecnologias utilizadas

- **Linguagem:** Java
- **Programação:** Orientação a Objetos (POO)
- **Estrutura de dados:** Stack (Pilha)
- **Tratamento de erros:** Try, Catch e Finally
- **IDE:** Visual Studio Code

## ⚙️ Funcionalidades

- Processamento de pagamentos de funcionários.
- Processamento de faturas.
- Cálculo de salários fixos.
- Cálculo de pagamentos por horas trabalhadas.
- Validação de dados.
- Verificação de jornada de trabalho.
- Controle de saldo empresarial.
- Histórico de transações.

## 🧩 Conceitos de programação aplicados

- **Interfaces:** definição de contratos de métodos.
- **Herança:** reutilização de atributos e métodos entre classes.
- **Polimorfismo:** processamento de diferentes tipos de pagamentos.
- **Encapsulamento:** controle de acesso aos atributos.
- **Exceções personalizadas:** identificação de erros específicos.
- **Exception Chaining:** encadeamento de exceções.
- **Try, Catch e Finally:** tratamento de situações inesperadas.

## 🧪 Cenários de testes

### Teste 1 – Caminho feliz
Processamento de pagamentos válidos, com saldo suficiente para todas as operações.

**Resultado esperado:** todos os pagamentos são aprovados.

### Teste 2 – Violação trabalhista
Simulação de funcionário com jornada diária de 12 horas.

**Resultado esperado:** o sistema identifica a irregularidade, interrompe o processamento e executa o rollback.

### Teste 3 – Falta de saldo
Simulação de processamento de cinco pagamentos, com saldo insuficiente para realizar o quarto pagamento.

**Resultado esperado:** o sistema interrompe o processamento e desfaz as três transações anteriores, seguindo a ordem LIFO.

## 👥 Integrantes do grupo

- Natan
- Davi
- Marcos
- Lucas Costa

## 🎓 Instituição

**Faculdade Estácio de Sá**

Juiz de Fora – Minas Gerais

## 📚 Considerações finais

O projeto demonstra a aplicação prática dos conceitos de programação orientada a objetos em Java, utilizando mecanismos de controle financeiro, tratamento de exceções e rollback para simular um processamento de pagamentos mais seguro e organizado.

---

<div align="center">

**Projeto acadêmico – Faculdade Estácio de Sá**

</div>
