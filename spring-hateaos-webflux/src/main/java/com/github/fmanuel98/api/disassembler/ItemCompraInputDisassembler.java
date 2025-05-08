package com.github.fmanuel98.api.disassembler;

import com.github.fmanuel98.api.model.input.ItemCompraInput;
import com.github.fmanuel98.domain.models.Cliente;
import com.github.fmanuel98.domain.models.ItemCompra;
import com.github.fmanuel98.domain.services.ProdutoService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ItemCompraInputDisassembler {
  private ModelMapper mapper;
  private ProdutoService serviceProdutoService;

  public ItemCompra toDomainObject(ItemCompraInput itemCompraInput) {
    var produto = serviceProdutoService.buscarOrFalhar(itemCompraInput.getProdutoId());
    int quantidadeDesejada = itemCompraInput.getQuantidade();
    var item = new ItemCompra();
    item.setQuantidade(quantidadeDesejada);
    item.setProduto(produto);
    item.setPrecoProduto(produto.getPreco());
    return item;
  }

  public void copyToDomainObject(ItemCompraInput clienteInput, Cliente cliente) {
    mapper.map(clienteInput, cliente);
  }
}
