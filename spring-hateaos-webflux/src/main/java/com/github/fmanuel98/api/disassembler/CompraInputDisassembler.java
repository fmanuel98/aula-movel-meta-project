package com.github.fmanuel98.api.disassembler;

import org.springframework.stereotype.Component;

import com.github.fmanuel98.api.model.input.CompraInput;
import com.github.fmanuel98.domain.models.Compra;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CompraInputDisassembler {

  public Compra toDomainObject(CompraInput compraInput) {
    var compra = new Compra();
    return compra;
  }

}
