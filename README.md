# OlabiBank Project

## Descrição

OlabiBank é um sistema bancário simples, desenvolvido para gerenciar clientes e suas contas bancárias. Este projeto inclui funcionalidades para criar clientes, criar contas, realizar depósitos, saques, transferências e pagamentos, além de permitir a atualização dos dados dos clientes e das contas.

## Funcionalidades

- **Clientes:**
    - Criar novo cliente.
    - Atualizar informações do cliente (exceto CPF).
    - Exibir lista de cliente do OlabiBank.
    - Buscar um cliente específico.
- **Contas:**
    - Criar conta para um cliente.
    - Realizar depósitos.
    - Realizar saques (4 saques mensais gratuitos, a partir do 5º saque, cobrança de R$ 6,50).
    - Realizar transferências entre contas.
    - Realizar pagamentos.
    - Listar todas as contas cadastradas no olabiBank.
    - Buscar por uma conta específica.
    - Atualizar informações de conta.

## Tecnologias Utilizadas

- ![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-%236DB33F.svg?style=for-the-badge&logo=spring-boot&logoColor=white)
- ![Hibernate](https://img.shields.io/badge/Hibernate-%230073A6.svg?style=for-the-badge&logo=hibernate&logoColor=white)
- ![JPA](https://img.shields.io/badge/JPA-%230073A6.svg?style=for-the-badge&logo=hibernate&logoColor=white)
- ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-%23336791.svg?style=for-the-badge&logo=postgresql&logoColor=white)
- ![Maven](https://img.shields.io/badge/Maven-%23C71A36.svg?style=for-the-badge&logo=apache-maven&logoColor=white)


## Dependências
As principais dependências do projeto incluem:

- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- PostgreSQL Driver
- Hibernate

## Endpoints da API

### Clientes

![img_1.png](src/main/resources/static/img_1.png)

### Conta

![img.png](src/main/resources/static/img.png)

## Como Testar Localmente

### Pré-requisitos

- ![Java](https://img.shields.io/badge/Java-17-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
- ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-%23336791.svg?style=for-the-badge&logo=postgresql&logoColor=white)
- ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-%23000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white) (*ou outra IDE de sua preferência*)


### Passos para Testar Localmente

1. **Clonar o repositório;**

2. **Configurar o banco de dados;**
   O projeto está usando PostgreSQL. A configuração do banco de dados pode ser alterada no `application.properties`

3. **Compilar e executar a aplicação;**
   - Executar maven clean e install
   - Rodar a aplicação

4. **Testar endpoints:**
    - Importar collection no postman/insomnia.
[Collection_OlabiBank_Insomnia](..%2FCollection_OlabiBank_Insomnia)

