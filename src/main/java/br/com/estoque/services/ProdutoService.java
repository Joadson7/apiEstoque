package br.com.estoque.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoque.dtos.ProdutoRequestDto;
import br.com.estoque.dtos.ProdutoResponseDto;
import br.com.estoque.entities.Produto;
import br.com.estoque.repositories.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoResponseDto criar(ProdutoRequestDto dto) {
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setUnidadeMedida(dto.unidade());
        produto.setCategoria(dto.categoria());

        produtoRepository.save(produto);
        return new ProdutoResponseDto(produto);
    }

    public List<ProdutoResponseDto> listarTodos() {
        return produtoRepository.findAll()
                .stream()
                .map(ProdutoResponseDto::new)
                .collect(Collectors.toList());
    }

    public ProdutoResponseDto buscarPorId(UUID id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        return new ProdutoResponseDto(produto);
    }

    public ProdutoResponseDto atualizar(UUID id, ProdutoRequestDto dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        produto.setNome(dto.nome());
        produto.setUnidadeMedida(dto.unidade());
        produto.setCategoria(dto.categoria());

        produtoRepository.save(produto);
        return new ProdutoResponseDto(produto);
    }

    public void desativar(UUID id) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        produto.setAtivo(false);
        produtoRepository.save(produto);
    }

	public List<ProdutoResponseDto> listarAtivos() {
		return produtoRepository.findAllAtivos().stream().map(ProdutoResponseDto::new).collect(Collectors.toList());
	}
}
