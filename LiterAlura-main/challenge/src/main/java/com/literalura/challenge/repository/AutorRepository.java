package com.literalura.challenge.repository;

import com.literalura.challenge.model.Autor;
import com.literalura.challenge.model.Idioma;
import com.literalura.challenge.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT l FROM Livro l JOIN l.autor a WHERE l.titulo ILIKE %:nome%")
    Optional<Livro> buscarLivroPorNome(@Param("nome") String nome);

    @Query("SELECT l FROM Autor a JOIN a.livros l")
    List<Livro> buscarTodosLivros();

    @Query("SELECT a FROM Autor a WHERE a.morte > :data")
    List<Autor> buscarAutoresVivos(@Param("data") String data);

    @Query("SELECT l FROM Autor a JOIN a.livros l WHERE l.idioma = :idioma")
    List<Livro> buscarLibrosIdioma(@Param("idioma") Idioma idioma);
}
