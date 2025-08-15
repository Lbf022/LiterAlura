package com.literalura.challenge.model;

import jakarta.persistence.*;

import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Double download;
    @ManyToOne
    private Autor autor;

    public Livro(){}

    public Livro(DadosLivros livroEncontrado) {
        this.titulo = livroEncontrado.titulo();
        this.download = livroEncontrado.download();
        this.idioma = Idioma.fromString(livroEncontrado.idiomas().stream()
                .limit(1).collect(Collectors.joining()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Idioma getIdiomas() {
        return idioma;
    }

    public void setIdiomas(Idioma idiomas) {
        this.idioma = idiomas;
    }

    public Double getDownload() {
        return download;
    }

    public void setDownload(Double download) {
        this.download = download;
    }

    @Override
    public String toString() {
        String mostrar = "--------------Livro---------------"+
                         "\nTitulo: " + titulo +
                         "\nIdioma: "  + idioma +
                         "\nNumero de downloads: " + download +
                         "\n-------------------------------------";

        return mostrar;
    }
}
