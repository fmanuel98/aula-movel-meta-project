package com.github.fmanuel98.api.disassembler;

import com.github.fmanuel98.api.model.input.CompraInput;
import com.github.fmanuel98.domain.models.Compra;
import com.github.fmanuel98.domain.services.ClienteService;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CompraInputDisassembler {
  private ClienteService clienteService;

  public Compra toDomainObject(CompraInput compraInput) {
    var compra = new Compra();
    var cliente = clienteService.buscarOrFalhar(compraInput.getClienteId());
    compra.setCliente(cliente);
    return compra;
  }

}
