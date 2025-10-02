package br.com.estoque.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estoque.entities.ProdutoEntrada;

@Repository
public interface ProdutoEntradaRepository extends JpaRepository<ProdutoEntrada, UUID> {
    
	 // Métodos específicos para cada combinação de filtros
    List<ProdutoEntrada> findByDataEntradaBetween(LocalDate dataInicio, LocalDate dataFim);
    List<ProdutoEntrada> findByProdutoId(UUID produtoId);
    List<ProdutoEntrada> findByDataEntradaBetweenAndProdutoId(LocalDate dataInicio, LocalDate dataFim, UUID produtoId);
    List<ProdutoEntrada> findByDataEntradaGreaterThanEqual(LocalDate dataInicio);
    List<ProdutoEntrada> findByDataEntradaLessThanEqual(LocalDate dataFim);
    
}