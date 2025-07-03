package br.com.estoque.dtos;

import java.time.LocalDate;
import java.util.UUID;

import br.com.estoque.entities.ProdutoSaida;
import br.com.estoque.enums.SetorDestino;

public record ProdutoSaidaResponseDto(
	    UUID id,
	    UUID produtoId,
	    String nomeProduto,
	    Integer quantidade,
	    SetorDestino destino,
	    LocalDate dataSaida
	) { 

	    public ProdutoSaidaResponseDto(ProdutoSaida saida) {
	        this(
	            saida.getId(),
	            saida.getProduto().getId(),
	            saida.getProduto().getNome(),
	            saida.getQuantidade(),
	            saida.getDestino(),
	            saida.getDataSaida()
	        );
	    }
	}
