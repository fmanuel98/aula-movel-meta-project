package com.github.fmanuel98.domain.services;

import java.util.List;

import com.github.fmanuel98.domain.models.Compra;
import com.github.fmanuel98.domain.models.ItemCompra;
import com.github.fmanuel98.domain.models.Produto;
import com.github.fmanuel98.domain.repositories.CompraRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class CompraService {
  private CompraRepository repository;

  public Compra salvar(Compra compra) {
    final var compraReal = compra;
    compraReal.getItemsCompra().forEach(item -> {
      item.setCompra(compraReal);
      var produto = verificarQuantidadeEmStock(item);
      item.setProduto(produto);
    });
    repository.save(compraReal);
    return compraReal;
  }

  private final Produto verificarQuantidadeEmStock(ItemCompra item) {
    var produto = item.getProduto();
    int quantidadeDisponivel = produto.getQuantidade();
    int quantidadeDesejada = item.getQuantidade();
    int quantidadeRestante = quantidadeDisponivel - quantidadeDesejada;
    if (quantidadeRestante < 0) {
      log.info("quantidade negativa");
    }
    produto.setQuantidade(quantidadeRestante);
    return produto;
  }

  public final List<Produto> produtoMaisVendidos() {
    // TODO depois impleme
    return List.of(new Produto());
  }

}
