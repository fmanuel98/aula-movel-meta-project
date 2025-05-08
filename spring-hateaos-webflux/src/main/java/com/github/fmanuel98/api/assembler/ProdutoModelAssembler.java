package com.github.fmanuel98.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.github.fmanuel98.api.model.ProdutoModel;
import com.github.fmanuel98.domain.models.Produto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoModelAssembler {
  @Autowired
  private ModelMapper mapper;

  public ProdutoModel toModel(Produto produto) {
    return mapper.map(produto, ProdutoModel.class);
  }

  public List<ProdutoModel> toCollectionModel(List<Produto> entities) {
    return entities.stream().map(this::toModel).collect(Collectors.toList());
  }

}
