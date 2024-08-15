# Documentação das classes do projeto
## URL

```shell
http://localhost:8080/api/v1/
```
## Controller

| Método                             | Endpoint                     | Descrição                  | Respostas                                                                                                                                 |
|------------------------------------|------------------------------|----------------------------|-------------------------------------------------------------------------------------------------------------------------------------------|
| POST                               | `/locations`                 | Cria um novo local.        | - 201: Local criado com sucesso. Retorna os dados do local criado.<br>- 400: Alguns dos campos estão inválidos.                           |
| GET                                | `/locations/getAllLocations` | Obtém todos os local.      | - 200: Lista de locais obtida com sucesso. Retorna um array contendo os locais.<br>- 404: Nenhum local encontrado.                        |
| GET                                | `/locations/{id}`            | Obtém um local pelo ID.    | - 200: Local encontrada. Retorna os dados do local.<br>- 404: Local não encontrado.                                                       |
| PUT                                | `/locations/{id}`            | Atualiza um local pelo ID. | - 200: Local atualizado com sucesso. Retorna os dados do local atualizado.<br>- 400: Requisição inválida.<br>- 404: Local não encontrads. |
| DELETE                             | `/locations/{id}`            | Deleta um local pelo ID.   | - 204: Local deletado com sucesso.<br>- 404: Local não encontrado.                                                                        |


## Model (Domain e Infra)

| Campo        | Tipo           | Descrição                                 |
|--------------|----------------|-------------------------------------------|
| id           | Long           | Identificador único do local.             |
| name         | String         | Nome do local.                            |
| neighborhood | String         | Bairro em que o local está.               |
| city         | String         | Cidade em que o local está.               |
| state        | LocalDateTime  | Estado em que o local está                |
| createdAt    | LocalDateTime  | Data e hora de criação do Local no banco. |
| updatedAt    | LocalDateTime  | Data e hora da última atualização.        |


## Service / Repository 

| Método                                       | Descrição                  |
|----------------------------------------------|----------------------------|
| `createLocation(Location location)`          | Cria um nova local.        |
| `getAllLocations()`                          | Lista todas os locais.     |
| `getLocationById(Long id)`                   | Encontra um local pelo ID. |
| `updateLocation(Location location, Long id)` | Atualiza um local pelo ID. |
| `deleteLocation(Long id)`                    | Deleta um local pelo ID.   |
