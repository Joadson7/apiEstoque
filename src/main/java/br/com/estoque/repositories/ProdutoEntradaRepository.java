package br.com.estoque.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estoque.entities.ProdutoEntrada;

public interface ProdutoEntradaRepository extends JpaRepository<ProdutoEntrada, UUID> {
	
}
