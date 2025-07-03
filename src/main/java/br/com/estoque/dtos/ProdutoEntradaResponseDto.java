package br.com.estoque.dtos;

import java.time.LocalDate;
import java.util.UUID;

import br.com.estoque.entities.ProdutoEntrada;

public record ProdutoEntradaResponseDto(
	    UUID id,
	    UUID produtoId,
	    String nomeProduto,
	    Integer quantidade,
	    String fornecedor,
	    LocalDate dataEntrada
	) {
	    public ProdutoEntradaResponseDto(ProdutoEntrada entrada) {
	        this(
	            entrada.getId(),
	            entrada.getProduto().getId(),
	            entrada.getProduto().getNome(),
	            entrada.getQuantidade(),
	            entrada.getFornecedor(),
	            entrada.getDataEntrada()
	        );
	    }
	}
