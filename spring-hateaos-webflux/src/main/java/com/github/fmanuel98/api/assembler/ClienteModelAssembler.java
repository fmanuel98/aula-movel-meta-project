package com.github.fmanuel98.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.github.fmanuel98.api.model.ClienteModel;
import com.github.fmanuel98.domain.models.Cliente;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteModelAssembler {
  @Autowired
  private ModelMapper mapper;

  public ClienteModel toModel(Cliente cliente) {
    return mapper.map(cliente, ClienteModel.class);
  }

  public List<ClienteModel> toCollectionModel(List<Cliente> entities) {
    return entities.stream().map(this::toModel).collect(Collectors.toList());
  }

}
