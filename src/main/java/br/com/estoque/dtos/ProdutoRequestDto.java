package br.com.estoque.dtos;

import br.com.estoque.enums.CategoriaProduto;
import br.com.estoque.enums.UnidadeDeMedida;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRequestDto(

    @NotBlank(message = "Por favor, informe o nome do produto.")
    String nome,

    @NotBlank(message = "Por favor, informe a unidade de medida do produto.")
    UnidadeDeMedida unidadeDeMedida,

    @NotNull(message = "Informe a categoria do produto.")
    CategoriaProduto categoria

) {}
