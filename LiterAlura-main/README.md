# LiterAlura
Challenge Literalura

## Descripcion
En este proyecto se busca utilizar la API de Gutendex disponible en https://gutendex.com/ con el objetivo de ofrecer un catalo de livros que el usuario pueda consultar. El programa cuenta con 5 opciones menu que son:

1.- Buscar libro por título. Busca y guarda un libro en la base de dados en caso de que aun no se haya guardado.

2.- Listar Libros registrados. Lista los livros registrados en la base de dados.

3.- Listar autores registrados. Lista los autores registrados en la base de dados.

4.- Listar autores vivos a partir de un determinado año. Pregunta a partir de que año se buscan autores que estuvieran vivos y los lista.

5.- Listar livros por idioma. Pregunta en que idioma se buscan livros de la base de dados y los lista. 

Cada una de estas opciones despliega sus resultados en la consola del IDE que estemos usando, en este caso IntelliJ. Este proyecto fue construido usando el framework de Spring Boot y las librerias Spring Data JPA, PostgreSQL Driver y Jackson Databind.


## Requisitos 
* Java (versión 17 en adelante)
* Maven 
* Spring Boot (versión 3.3.1)
* PostgreSQL
* IDE Java (Eclipse, IntelliJ IDEA, etc.)

Es importante mencionar que cierta informacion como la contraseña de la base de dados se maneja como variable de entorno por lo que hay que tenerlo en consideración al momento de ser ejecutado el programa, ya que si no estan definidas dichas variables, no se podra enlazar la base de dados. 


