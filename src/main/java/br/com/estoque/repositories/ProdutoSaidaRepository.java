package br.com.estoque.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.estoque.entities.ProdutoSaida;

public interface ProdutoSaidaRepository extends JpaRepository<ProdutoSaida, UUID> {
	
	    List<ProdutoSaida> findByDataSaidaBetween(LocalDate dataInicio, LocalDate dataFim);
	    List<ProdutoSaida> findByProdutoId(UUID produtoId);
	    List<ProdutoSaida> findByDataSaidaBetweenAndProdutoId(LocalDate dataInicio, LocalDate dataFim, UUID produtoId);
	    List<ProdutoSaida> findByDataSaidaGreaterThanEqual(LocalDate dataInicio);
	    List<ProdutoSaida> findByDataSaidaLessThanEqual(LocalDate dataFim);
}
