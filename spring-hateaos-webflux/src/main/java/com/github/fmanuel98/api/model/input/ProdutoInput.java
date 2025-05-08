package com.github.fmanuel98.api.model.input;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class ProdutoInput {
  @NotBlank
  @Size(min = 2, max = 54)
  private String nome;
  @Positive
  private BigDecimal preco;
  @PositiveOrZero
  private Integer quantidade;
  @Future
  private LocalDate dataExpiracao;
}
