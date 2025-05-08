package com.github.fmanuel98.api.controllers;

import java.util.List;

import jakarta.validation.Valid;

import com.github.fmanuel98.api.assembler.ClienteModelAssembler;
import com.github.fmanuel98.api.disassembler.ClienteInputDisassembler;
import com.github.fmanuel98.api.model.ClienteModel;
import com.github.fmanuel98.api.model.input.ClienteInput;
import com.github.fmanuel98.domain.repositories.ClienteRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {
  private ClienteRepository repository;
  private ClienteInputDisassembler disassembler;
  private ClienteModelAssembler assembler;

  @GetMapping
  public List<ClienteModel> listar() {
    var clientes = repository.findAll();
    return assembler.toCollectionModel(clientes);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ClienteModel salvar(@Valid @RequestBody ClienteInput clienteInput) {
    var cliente = disassembler.toDomainObject(clienteInput);
    cliente = repository.save(cliente);
    return assembler.toModel(cliente);
  }
}
