# Api relatório vendas

## INTRODUÇÃO

Este projeto é uma API de vendas que também possibilita a consulta do número de vendas dos vendedores por período.

Realizo então o **BACKEND** para todo o funcionamento da mesma, realizando o **CRUD** completo para todas entidades com implementação de:

- Validações
- Filtros
- Dtos
- E muito mais...

# Tecnologias utilizadas

- Java
- Spring Boot
- JPA / Hibernate
- Maven
- Lombok
- Validation
- Postgresql

# Pré-requisitos para executar a api

- Java 17
- Postegresql
- Postman

# End-points disponíveis

![endpoints](https://github.com/leonardoeloicorrea/api-relatorio-vendas/assets/99756074/6b85d8f8-9c61-4e4f-8a88-28ffe6c4b753)

# Api em funcionamento

Para que seja possível visualizar a API em funcionamento, é necessário a existência de determinadas entidades como Sellers e Sales presentes em nosso banco de dados. Então, suponha que no banco de dados vinculado a nossa API possua os seguintes valores inseridos para cada entidade de nosso sistema.

## Valores para entidade “Seller”:

![Seller](https://github.com/leonardoeloicorrea/api-relatorio-vendas/assets/99756074/2df1c860-1f34-4b5d-9ff0-d91e47cc69d5)

## Valores para entidade “Sale”.

![Sales](https://github.com/leonardoeloicorrea/api-relatorio-vendas/assets/99756074/662d311d-c1c6-4ca4-9f66-f4d48e88b3eb)

# Resposta a requisições

veja agora alguns exemplos de como a API responde a determinadas requisições **Rest**, levando em consideração os valores mencionados para cada respectiva entidade.

### 1) Requisição do tipo CREATE para entidade Seller.

Nesse caso, estamos realizando uma requisição para **CRIAR** um novo **“Seller”**.

![1](https://github.com/leonardoeloicorrea/api-relatorio-vendas/assets/99756074/62a7a3ab-d0de-48d0-a617-760de378f77f)

- Passamos os valores dos campos **“name”**, **“cpf”** e **“dateOfBirth”** no corpo da requisição para criar um novo **“Seller**”.

### Resposta da requisição:

![2](https://github.com/leonardoeloicorrea/api-relatorio-vendas/assets/99756074/8f02bd74-4c2b-4c08-8816-97ed1e35a559)

- A resposta da requisição consiste em um **DTO** que nesse caso inclui os valores passados na requisição como também os campos **“id”** e **“age”** cujo seus valores foram gerados de acordo com a lógica de negócio. Foi retornado também o status do tipo **“201 Created”** indicando que a entidade foi criada com sucesso.

### 2) Requisição do tipo GET para entidade Seller.

Nesse caso, estamos realizando uma requisição para **PEGAR** um **“Seller”**.

Suponha que foi requisitado um **Seller** cujo campo **“id”** seja igual ao numero **“2”**.

### Resposta da requisição:

![3](https://github.com/leonardoeloicorrea/api-relatorio-vendas/assets/99756074/7cc7ea13-1570-4a30-a39a-702fe06ae77b)

- A resposta da requisição consiste em um **DTO** com os dados da entidade **Seller** cujo **ID** é igual ao numero **“2”** como solicitado pelo cliente. Foi retornado também o status do tipo **“200 OK”** indicando que a requisição foi processada com sucesso.

### 3) Requisição do tipo GET para entidade Seller.

Nesse caso, estamos realizando uma requisição para **PEGAR** a media de vendas diária de cada **“Seller”** de acordo com um período especificado.

![4](https://github.com/leonardoeloicorrea/api-relatorio-vendas/assets/99756074/e8506d75-1bdb-4b05-91ce-1583b05eff53)

- Passamos os valores dos campos **“initialDate”** e **“finalDate”** conforme necessário na requisição.

### Resposta da requisição:

![5](https://github.com/leonardoeloicorrea/api-relatorio-vendas/assets/99756074/a283f6fa-8afe-4828-93a1-2f32d2bea789)

- Foi retornado um **DTO** representando alguns parâmetros relacionados a entidade **“Seller”** com base no período especificado. Também foi retornado o status do tipo **“200 OK”** indicando que a requisição foi processada com sucesso.

### 4) Requisição do tipo PUT para entidade Seller.

Nesse caso, estamos realizando uma requisição para **ATUALIZAR** a entidade **“Seller”**.

Suponha que foi requisitado a atualização do **Seller** cujo campo **“id”** seja igual ao numero **“3**”.

![6](https://github.com/leonardoeloicorrea/api-relatorio-vendas/assets/99756074/0c00d171-ff27-4c7f-ae24-30d647f7f3e7)

Perceba que estamos alterando apenas o nome da entidade **Seller**.

### Resposta da requisição:

![7](https://github.com/leonardoeloicorrea/api-relatorio-vendas/assets/99756074/ceeda98d-875f-4be2-a1c1-3c2c66f2d5fc)

- O resultado da nossa requisição é um **DTO** representando a entidade **Seller** atualizada, juntamente com o status do tipo **“200 OK”** indicando que a requisição foi processada com sucesso.

### 5) Requisição do tipo DELETE para entidade Seller.

Nesse caso, estamos realizando uma requisição para **DELETAR** a entidade **“Seller”**.

Suponha que foi requisitado a deleção do **Seller** cujo campo **“id”** seja igual ao numero **“3”**.

### Resposta da requisição:

![8](https://github.com/leonardoeloicorrea/api-relatorio-vendas/assets/99756074/14633d54-5355-4219-ac27-c172f3e9f106)

- O resultado da nossa requisição é um **OBJECT** representando a resposta da requisição, que nesse caso é uma mensagem indicando que o **Seller** foi deletado. Juntamente com o status do tipo **“200 OK”** indicando que a requisição foi processada com sucesso.

# Tratamento de exceções

- Para que seja possível visualizar de maneira mais específica as exceções que ocorrem no decorrer do uso da api, realizamos o tratamento de exceções que tem como objetivo especificar de maneira mais clara e detalhada a exceção retornada no corpo da requisição.

Levando em consideração os dados de exemplo destacados anteriormente para as entidades **“Seller”** e **“Sale”**, veja como é retornada algumas exceções que foram **“tratadas”** corretamente.

### 1) Exceção retornada para CPF inválido na tentativa de CRIAR um novo SELLER.

![9](https://github.com/leonardoeloicorrea/api-relatorio-vendas/assets/99756074/3177ed4f-a3db-492a-9e53-f2b360108808)

- Perceba que estamos realizando a tentativa de criar um novo **SELLER** passando um **CPF** já existente no banco de dados.

### Resposta da requisição:

![10](https://github.com/leonardoeloicorrea/api-relatorio-vendas/assets/99756074/1e3301bd-7164-4858-bfe9-3559d71b1325)

- O resultado da nossa requisição foi a exceção personalizada na qual retornou o **STATUS** que nesse caso é do tipo **“400 Bad Request”** indicando que ocorreu um erro por parte do cliente, o **ERROR** que nesse caso retornou uma mensagem indicando que o **CPF** passado como parâmetro já existe no banco de dados e mais algumas outras informações.

### 2) Exceção retornada para NAME inválido na tentativa de CRIAR um novo SELLER.

![11](https://github.com/leonardoeloicorrea/api-relatorio-vendas/assets/99756074/f228b6c5-e2c9-47e5-99f3-e89bbe6efc7a)

- Perceba que estamos realizando a tentativa de criar um novo **SELLER** passando um valor vazio no campo **NAME**.

### Resposta da requisição:

![12](https://github.com/leonardoeloicorrea/api-relatorio-vendas/assets/99756074/c38aaa5b-bbf3-47d9-8f7d-36bd01273eab)

- O resultado da nossa requisição foi a exceção personalizada na qual retornou diversas informações, dentre elas o **STATUS** que nesse caso está indicando que ocorreu um possível **erro de validação**, o **ERROR** que nesse caso retornou uma mensagem indicando que o **NAME** é um campo obrigatório e que o seu valor deve ser entre **3** a **100** caracteres.

### 3) Exceção retornada para SELLER ID inválido na tentativa de CRIAR um novo SELLER.

![13](https://github.com/leonardoeloicorrea/api-relatorio-vendas/assets/99756074/30cf4cc7-29f4-48d7-8343-9ada57d7911f)

- Perceba que estamos realizando a tentativa de criar uma nova venda passando como valor para o campo **SELLER ID** um **id** de um **SELLER** que não existe.

### Resposta da requisição:

![14](https://github.com/leonardoeloicorrea/api-relatorio-vendas/assets/99756074/f161206d-d098-426a-b948-0e8c62ab8aa9)

- O resultado da nossa requisição foi a exceção personalizada na qual retornou diversas informações, dentre elas o **STATUS** que nesse caso está indicando **“Não encontrado”** e o **ERROR** que nesse caso está indicando que não foi encontrado nenhum **SELLER** vinculado ao **ID** passado como parâmetro.

# CONCLUSÃO

Então como vimos, para **CRIAR** uma nova **SALE**, precisamos previamente **CRIAR** um novo **SELLER** para que possamos utilizar o **ID** desse **SELLER** como referência para então atrelar a SALE a um SELLER e por fim, poder realizar o uso da requisição para descobrir a média de vendas diária conforme período especificado.
