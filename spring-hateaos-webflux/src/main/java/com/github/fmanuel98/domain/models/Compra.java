package com.github.fmanuel98.domain.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "compra")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Compra {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;
  @OneToMany(mappedBy = "compra", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
  private List<ItemCompra> itemsCompra;
  @ManyToOne(optional = false)
  private Cliente cliente;
  private BigDecimal total;
  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  private LocalDate createdAt;
  @Column(nullable = false)
  @UpdateTimestamp
  private LocalDate updatedAt;

  @PreUpdate
  @PrePersist
  public void calcularTotal() {
    var total = this.itemsCompra.stream().map(i -> {
      i.calcularSubTotal();
      return i.getSubTotal();
    }).reduce(BigDecimal.ZERO, BigDecimal::add);
    this.setTotal(total);
  }
}
