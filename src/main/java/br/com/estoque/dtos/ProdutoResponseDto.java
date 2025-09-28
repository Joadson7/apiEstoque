package br.com.estoque.dtos;

import java.util.UUID;

import br.com.estoque.entities.Produto;
import br.com.estoque.enums.CategoriaProduto;

public record ProdutoResponseDto(
    UUID id,
    String nome,
    String unidade,
    CategoriaProduto categoria,
    Integer estoqueAtual,
    Boolean ativo
) {
    public ProdutoResponseDto(Produto produto) {
        this(
            produto.getId(),
            produto.getNome(),
            produto.getUnidadeMedida(),
            produto.getCategoria(), // aqui já retorna o enum
            produto.getEstoqueAtual(),
            produto.getAtivo()
            
        );
    }
}
