package com.github.fmanuel98.api.model.input;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProdutoInput {
  @NotBlank
  @Size(min = 2, max = 54)
  private String nome;
  @Positive
  private BigDecimal preco;
  @PositiveOrZero
  private Integer quantidade;
  @NotNull
  private MultipartFile image;

}
