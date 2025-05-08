package com.github.fmanuel98.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCompraModel {
  private Long id;
  private Produto produto;
  private String nomeProduto;
  private BigDecimal precoProduto;
  private BigDecimal subTotal;
  private Integer quantidade;
  private LocalDate createdAt;
  private LocalDate updatedAt;

}

@Getter
@Setter
class Produto {
  private Long id;
}