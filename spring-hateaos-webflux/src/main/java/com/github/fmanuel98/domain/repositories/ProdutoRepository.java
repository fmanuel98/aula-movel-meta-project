package com.github.fmanuel98.domain.repositories;

import com.github.fmanuel98.domain.models.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
