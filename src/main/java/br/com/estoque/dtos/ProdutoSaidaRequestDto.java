package br.com.estoque.dtos;

import java.time.LocalDate;
import java.util.UUID;

import br.com.estoque.enums.SetorDestino;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProdutoSaidaRequestDto(

	    @NotNull(message = "Por favor, informe o id do produto.")
	    UUID produtoId,

	    @NotNull(message = "Por favor, informe a quantidade.")
	    @Min(value = 1, message = "A quantidade deve ser maior que zero.")
	    Integer quantidade,

	    @NotNull(message = "Por favor, informe a data da saída.")
	    LocalDate dataSaida,

	    @NotNull(message = "Por favor, informe o destino da saída.")
	    SetorDestino destino

	) {}
