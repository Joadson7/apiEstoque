package br.com.estoque.dtos;

import java.util.UUID;

import br.com.estoque.entities.Produto;
import br.com.estoque.enums.CategoriaProduto;
import br.com.estoque.enums.UnidadeDeMedida;

public record ProdutoResponseDto(
    UUID id,
    String nome,
    UnidadeDeMedida unidadeDeMedida,
    CategoriaProduto categoria,
    Integer estoqueAtual,
    Boolean ativo
) {
    public ProdutoResponseDto(Produto produto) {
        this(
            produto.getId(),
            produto.getNome(),
            produto.getUnidadeDeMedida(), // converte o enum para String
            produto.getCategoria(), // aqui já retorna o enum
            produto.getEstoqueAtual(),
            produto.getAtivo()
            
        );
    }
}
