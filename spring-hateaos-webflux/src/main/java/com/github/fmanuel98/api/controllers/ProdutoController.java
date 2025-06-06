package com.github.fmanuel98.api.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fmanuel98.api.NovoArquivo;
import com.github.fmanuel98.api.assembler.ProdutoModelAssembler;
import com.github.fmanuel98.api.disassembler.ProdutoInputDisassembler;
import com.github.fmanuel98.api.model.ProdutoModel;
import com.github.fmanuel98.api.model.input.ProdutoInput;
import com.github.fmanuel98.domain.repositories.ProdutoRepository;
import com.github.fmanuel98.domain.services.ProdutoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/produtos")
@AllArgsConstructor
public class ProdutoController {
  private ProdutoService service;
  private ProdutoRepository repository;
  private ProdutoInputDisassembler disassembler;
  private ProdutoModelAssembler assembler;

  @GetMapping
  public List<ProdutoModel> listar() {
    var produtos = repository.findAll();
    return assembler.toCollectionModel(produtos);
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ProdutoModel salvar(@Valid ProdutoInput input) {
    armazenar(input);
    return null;
  }

  private Path getArquivoPath(String nomeArquivo) throws IOException {
    System.out.println(System.getProperty("user.home"));
    var path = Paths.get(System.getProperty("user.home"), "desktop", "fotos").resolve(Path.of(nomeArquivo));
    System.out.println(path.toAbsolutePath());
    System.out.println(path.getParent());
    if (Files.notExists(path)) {
      System.out.println("criadn");
      Files.createDirectories(Paths.get(System.getProperty("user.home"), "desktop", "fotos"));
    }
    return path;
  }

  private void armazenar(ProdutoInput input) {
    var file = input.getImage();
    System.out.println("===================");
    System.out.println(file.getOriginalFilename());
    try {
      var novaFoto = NovoArquivo.builder().inputStream(file.getInputStream())

          .nomeAquivo(file.getOriginalFilename()).build();

      Path arquivoPath = getArquivoPath(novaFoto.getNomeAquivo());

      FileCopyUtils.copy(novaFoto.getInputStream(),
          Files.newOutputStream(arquivoPath));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

}
