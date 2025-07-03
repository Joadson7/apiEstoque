package br.com.estoque.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.estoque.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
	
	@Query("SELECT p FROM Produto p WHERE p.ativo = true")
	List<Produto> findAllAtivos();
	
}