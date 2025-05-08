package com.github.fmanuel98.api.model.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemCompraInput {
  @NotNull
  @EqualsAndHashCode.Include
  private Long produtoId;
  @NotNull
  @Positive
  private Integer quantidade;
}
