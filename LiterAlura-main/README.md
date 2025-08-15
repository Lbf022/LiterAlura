# 📚 LiterAlura

Catálogo de Livros usando a API Gutendex

## 📖 Sobre o Projeto

O **LiterAlura** é uma aplicação Java desenvolvida com **Spring Boot** que consome a [API Gutendex](https://gutendex.com/) para oferecer um catálogo de livros pesquisáveis.
Os dados são armazenados em um banco de dados **PostgreSQL** para consultas futuras.

O sistema oferece **5 funcionalidades principais**:

1. **Buscar livro por título**

    * Consulta a API Gutendex e salva o livro no banco de dados caso ainda não esteja registrado.

2. **Listar livros registrados**

    * Exibe todos os livros armazenados no banco de dados.

3. **Listar autores registrados**

    * Mostra todos os autores já cadastrados.

4. **Listar autores vivos a partir de um determinado ano**

    * Solicita um ano de referência e retorna autores que estavam vivos a partir dessa data.

5. **Listar livros por idioma**

    * Filtra e exibe os livros registrados conforme o idioma informado.

💡 Todas as interações acontecem pelo **console** do IDE (ex.: IntelliJ IDEA).

---

## 🛠 Tecnologias Utilizadas

* **Java 17+**
* **Maven**
* **Spring Boot 3.3.1**
* **Spring Data JPA**
* **PostgreSQL Driver**
* **Jackson Databind**

---

## 📋 Pré-requisitos

Antes de executar o projeto, você precisará ter instalado:

* [Java 17 ou superior](https://adoptium.net/)
* [Maven](https://maven.apache.org/)
* [PostgreSQL](https://www.postgresql.org/)
* IDE Java (Eclipse, IntelliJ IDEA, VS Code com extensão Java, etc.)

Além disso, configure as seguintes **variáveis de ambiente** para conexão ao banco de dados:

| Variável      | Descrição                              |
| ------------- | -------------------------------------- |
| `DB_HOST`     | Host do banco de dados                 |
| `DB_PORT`     | Porta do banco de dados (padrão: 5432) |
| `DB_NAME`     | Nome do banco de dados                 |
| `DB_USER`     | Usuário do banco de dados              |
| `DB_PASSWORD` | Senha do banco de dados                |

⚠ **Importante:** Sem essas variáveis, a aplicação não conseguirá se conectar ao banco de dados.

---

## ▶️ Como Executar

1. **Clonar o repositório**

   ```bash
   git clone https://github.com/usuario/literalura.git
   cd literalura
   ```

2. **Compilar e executar**

   ```bash
   mvn spring-boot:run
   ```

3. **Interagir pelo console**

    * Escolha as opções do menu para consultar e gerenciar o catálogo de livros.

---


