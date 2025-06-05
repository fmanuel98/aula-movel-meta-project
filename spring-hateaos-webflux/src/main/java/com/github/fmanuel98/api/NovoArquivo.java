package com.github.fmanuel98.api;

import java.io.InputStream;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NovoArquivo {
    private String nomeAquivo;
    private InputStream inputStream;
}
