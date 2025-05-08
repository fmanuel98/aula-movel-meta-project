package com.github.fmanuel98.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import com.github.fmanuel98.api.assembler.CompraModelAssembler;
import com.github.fmanuel98.api.disassembler.CompraInputDisassembler;
import com.github.fmanuel98.api.disassembler.ItemCompraInputDisassembler;
import com.github.fmanuel98.api.model.CompraModel;
import com.github.fmanuel98.api.model.input.CompraInput;
import com.github.fmanuel98.domain.repositories.CompraRepository;
import com.github.fmanuel98.domain.services.CompraService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/compras")
@AllArgsConstructor
public class CompraController {
  private CompraRepository repository;
  private CompraService service;
  private CompraInputDisassembler disassembler;
  private ItemCompraInputDisassembler itemInputDisassembler;
  private CompraModelAssembler assembler;

  @GetMapping
  public List<CompraModel> listar() {
    var compras = repository.findAll();
    return assembler.toCollectionModel(compras);
  }

  @PostMapping
  public CompraModel salvar(@Valid @RequestBody CompraInput compraInput) {
    final var itens = compraInput.getItemsCompra().stream()
        .map(itemInput -> itemInputDisassembler.toDomainObject(itemInput)).collect(Collectors.toList());
    var compra = disassembler.toDomainObject(compraInput);
    compra.setItemsCompra(itens);
    compra = service.salvar(compra);
    return assembler.toModel(compra);
  }
}
