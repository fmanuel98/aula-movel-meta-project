package com.github.fmanuel98.api.model.input;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CompraInput {
  @NotNull
  @NotEmpty
  private List<ItemCompraInput> itemsCompra;
  @NotNull
  private Long clienteId;
}
