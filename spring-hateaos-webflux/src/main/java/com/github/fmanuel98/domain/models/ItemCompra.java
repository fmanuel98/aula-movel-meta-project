package com.github.fmanuel98.domain.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "item_compra")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemCompra {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional = false, cascade = CascadeType.MERGE)
  @EqualsAndHashCode.Include
  private Produto produto;
  @ManyToOne(optional = false)
  private Compra compra;
  @Column(nullable = false)
  private BigDecimal precoProduto;
  @Column(nullable = false)
  private BigDecimal subTotal;
  @Column(nullable = false)
  private Integer quantidade;
  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  private LocalDate createdAt;
  @Column(nullable = false)
  @UpdateTimestamp
  private LocalDate updatedAt;

  @PostLoad
  @PrePersist
  @PreUpdate
  public void calcularSubTotal() {
    var subTotal = this.precoProduto.multiply(BigDecimal.valueOf(this.quantidade));
    this.setSubTotal(subTotal);
  }
}
