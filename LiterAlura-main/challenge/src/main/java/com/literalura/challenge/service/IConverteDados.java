package com.literalura.challenge.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> clase);
}
