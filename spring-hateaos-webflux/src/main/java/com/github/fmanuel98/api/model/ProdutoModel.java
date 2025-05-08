package com.github.fmanuel98.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoModel {
  private Long id;
  private String nome;
  private BigDecimal preco;
  private Integer quantidade;
  private LocalDate dataExpiracao;
  private LocalDate createdAt;
  private LocalDate updatedAt;
}
