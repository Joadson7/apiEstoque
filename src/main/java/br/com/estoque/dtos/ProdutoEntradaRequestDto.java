package br.com.estoque.dtos;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProdutoEntradaRequestDto(
	
    @NotNull(message = "Por favor, informe o id da categoria.")
	UUID produtoId,
	
	@NotNull(message = "Por favor, informe a quantidade do produto.")
	@Min(value = 1, message = "A quantidade deve ser maior que zero.")
	Integer quantidade,
	
	@NotNull(message = "Por favor, informe a data de entrada.")
    LocalDate dataEntrada,
    
    @NotEmpty(message = "Por favor, informe o fornecedor do produto.")
    String fornecedor
		) {}

