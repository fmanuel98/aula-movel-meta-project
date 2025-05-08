package com.github.fmanuel98.api.model.input;

import java.math.BigDecimal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class ClienteInput {
  @Size(min = 2, max = 25)
  private String nome;
  @Email
  @Size(min = 10, max = 75)
  private String email;
  @Positive
  @NotNull
  private BigDecimal money;
}
