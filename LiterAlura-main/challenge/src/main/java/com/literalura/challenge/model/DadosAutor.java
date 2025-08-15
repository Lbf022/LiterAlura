package com.literalura.challenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosAutor(
        @JsonAlias("name") String nome,
        @JsonAlias("birth_year") String nascimento,
        @JsonAlias("death_year") String morte) {
}
