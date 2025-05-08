package com.github.fmanuel98.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompraModel {
  private Long id;
  private List<ItemCompraModel> itemsCompra;
  private ClienteModel cliente;
  private BigDecimal total;
  private LocalDate createdAt;
  private LocalDate updatedAt;
}
