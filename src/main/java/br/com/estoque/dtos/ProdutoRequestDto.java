package br.com.estoque.dtos;

import br.com.estoque.enums.CategoriaProduto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRequestDto(

    @NotBlank(message = "Por favor, informe o nome do produto.")
    String nome,

    @NotBlank(message = "Por favor, informe a unidade de medida (ex: UN, KG, LT).")
    String unidade,

    @NotNull(message = "Informe a categoria do produto.")
    CategoriaProduto categoria

) {}
