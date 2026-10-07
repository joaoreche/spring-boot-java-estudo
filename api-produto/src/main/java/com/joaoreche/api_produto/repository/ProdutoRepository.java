package com.joaoreche.api_produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaoreche.api_produto.model.Produto;

/**
 * Repositório JPA para operações de persistência da entidade {@link Produto}.
 *
 * Ao estender JpaRepository, herda automaticamente os principais
 * métodos CRUD sem necessidade de implementação manual.
 */
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
