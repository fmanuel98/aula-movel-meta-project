package com.github.fmanuel98.domain.services;

import com.github.fmanuel98.domain.models.Cliente;
import com.github.fmanuel98.domain.repositories.ClienteRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService {
  private ClienteRepository repository;

  public Cliente buscarOrFalhar(Long clienteId) {
    return repository.findById(clienteId).orElseThrow(() -> new NullPointerException(""));
  }
}
