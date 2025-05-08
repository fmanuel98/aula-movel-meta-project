package com.github.fmanuel98.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ClienteModel {
  private Long id;
  private String nome;
  private String email;
  private BigDecimal money;
  private LocalDate createdAt;
  private LocalDate updatedAt;
}
