package com.github.fmanuel98.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.github.fmanuel98.api.model.CompraModel;
import com.github.fmanuel98.domain.models.Compra;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompraModelAssembler {
  @Autowired
  private ModelMapper mapper;

  public CompraModel toModel(Compra compra) {
    return mapper.map(compra, CompraModel.class);
  }

  public List<CompraModel> toCollectionModel(List<Compra> entities) {
    return entities.stream().map(this::toModel).collect(Collectors.toList());
  }

}
