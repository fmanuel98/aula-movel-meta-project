package com.github.fmanuel98.api.disassembler;

import com.github.fmanuel98.api.model.input.ProdutoInput;
import com.github.fmanuel98.domain.models.Produto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProdutoInputDisassembler {
  private ModelMapper mapper;

  public Produto toDomainObject(ProdutoInput produtoInput) {
    return mapper.map(produtoInput, Produto.class);
  }

  public void copyToDomainObject(ProdutoInput produtoInput, Produto produto) {
    mapper.map(produtoInput, produto);
  }
}
