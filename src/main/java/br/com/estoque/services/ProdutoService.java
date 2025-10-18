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
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoResponseDto criar(ProdutoRequestDto dto) {
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setUnidadeDeMedida(dto.unidadeDeMedida());
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
    
    public List<ProdutoResponseDto> listarAtivos() {
        return produtoRepository.findAllAtivos()
                .stream()
                .map(ProdutoResponseDto::new)
                .toList();
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
        produto.setUnidadeDeMedida(dto.unidadeDeMedida());
        produto.setCategoria(dto.categoria());

        produtoRepository.save(produto);
        return new ProdutoResponseDto(produto);
    }
    
    @Transactional
    public void desativar(UUID id) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        produto.setAtivo(false);
        produtoRepository.save(produto);
    }

    public List<ProdutoResponseDto> listarComFiltros(String nome, String categoria, String dataInicio, String dataFim) {
        List<Produto> produtos = produtoRepository.findAllAtivos();
        
        return produtos.stream()
            .filter(produto -> filtrarPorNome(produto, nome))
            .filter(produto -> filtrarPorCategoria(produto, categoria))
            .filter(produto -> filtrarPorData(produto, dataInicio, dataFim))
            .map(ProdutoResponseDto::new) // ← Usando o constructor do record
            .collect(Collectors.toList());
    }

    private boolean filtrarPorNome(Produto produto, String nome) {
        return nome == null || nome.trim().isEmpty() || 
               produto.getNome().toLowerCase().contains(nome.toLowerCase());
    }

    private boolean filtrarPorCategoria(Produto produto, String categoria) {
        return categoria == null || categoria.trim().isEmpty() || 
               produto.getCategoria().name().equals(categoria);
    }

    private boolean filtrarPorData(Produto produto, String dataInicio, String dataFim) {
        // Se seus produtos não têm data de cadastro, retorne true
        // Se tiverem, implemente a lógica de data
        return true;
    }
}
