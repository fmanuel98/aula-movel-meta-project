package com.github.fmanuel98.domain.repositories;

import com.github.fmanuel98.domain.models.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
