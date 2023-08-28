
# API LIVRARIA

API de uma biblioteca, feita com Java - Spring Boot. Desafio proposto para aprofundar conhecimentos e ver nível de habilidades.


## Instalação

Para rodar o projeto você deve ter o Java instalado e usar o [Postman](https://www.postman.com/downloads/) para fazer as requisições, nesta API estamos usando as seguintes versões:


```bash
  java: '17'
  springframework.boot: '3.1.2'
  gerenciador de pacotes: gradle  
```

Caso deseje rodar os testes no terminal, se estiver no Windows rode o seguinte comando, na pasta raiz do projeto.

```bash
    gradlew test
```
## Screenshots

Exemplo:

![App Screenshot](https://i.ibb.co/Nj5sdWj/test.png)


## Documentação da API

#### Retorna todos os autores
```http
  GET /autores
```
#### Retorna um autor pelo seu id
```http
  GET /autores/{id}
```
#### Atualiza um autor pelo seu id
```http
  PUT /autores/{id}
```
#### Deleta um autor pelo seu id

```http
  DELETE /autores/{id}
```

#### As outras classes de modelo seguem os mesmos modelos de endpoints.

## Funcionalidades

- Criar Autor
- Criar Livro
- Criar Locatario



## Autor

- [LinkedIn](https://www.linkedin.com/in/mateuspereira-developer/)

- [Github](https://github.com/mateusmaiadb)

