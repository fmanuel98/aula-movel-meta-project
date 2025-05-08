package com.github.fmanuel98.domain.services;

import com.github.fmanuel98.domain.models.Produto;
import com.github.fmanuel98.domain.repositories.ProdutoRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProdutoService {
  private ProdutoRepository repository;

  public Produto salvar(Produto produto) {
    return repository.save(produto);
  }

  public Produto buscarOrFalhar(Long produtoId) {
    return repository.findById(produtoId).orElseThrow(() -> new NullPointerException("jhhhhhhhhhhhhhhh"));
  }
}
