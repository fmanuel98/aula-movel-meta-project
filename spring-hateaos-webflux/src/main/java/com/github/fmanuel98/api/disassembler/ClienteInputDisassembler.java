package com.github.fmanuel98.api.disassembler;

import com.github.fmanuel98.api.model.input.ClienteInput;
import com.github.fmanuel98.domain.models.Cliente;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ClienteInputDisassembler {
  private ModelMapper mapper;

  public Cliente toDomainObject(ClienteInput clienteInput) {
    return mapper.map(clienteInput, Cliente.class);
  }

  public void copyToDomainObject(ClienteInput clienteInput, Cliente cliente) {
    mapper.map(clienteInput, cliente);
  }
}
