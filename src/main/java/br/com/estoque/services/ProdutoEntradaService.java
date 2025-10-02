package br.com.estoque.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoque.dtos.ProdutoEntradaRequestDto;
import br.com.estoque.dtos.ProdutoEntradaResponseDto;
import br.com.estoque.entities.Produto;
import br.com.estoque.entities.ProdutoEntrada;
import br.com.estoque.repositories.ProdutoEntradaRepository;
import br.com.estoque.repositories.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoEntradaService {

    @Autowired
    private ProdutoEntradaRepository entradaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoEntradaResponseDto criar(ProdutoEntradaRequestDto dto) {
        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        // Verifica se o produto está ativo
        produto.setAtivo(true);
        
        ProdutoEntrada entrada = new ProdutoEntrada();
        entrada.setProduto(produto);
        entrada.setQuantidade(dto.quantidade());
        entrada.setDataEntrada(dto.dataEntrada());
        entrada.setFornecedor(dto.fornecedor());

        // Atualiza o estoque do produto
        Integer estoqueAtual = produto.getEstoqueAtual() != null ? produto.getEstoqueAtual() : 0;
        produto.setEstoqueAtual(estoqueAtual + dto.quantidade());

        // Salva ambos
        produtoRepository.save(produto);
        entradaRepository.save(entrada);

        return new ProdutoEntradaResponseDto(entrada);
    }

    public List<ProdutoEntradaResponseDto> listarTodos() {
        return entradaRepository.findAll()
                .stream()
                .map(ProdutoEntradaResponseDto::new)
                .collect(Collectors.toList());
    }

    public ProdutoEntradaResponseDto buscarPorId(UUID id) {
        ProdutoEntrada entrada = entradaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entrada não encontrada"));
        return new ProdutoEntradaResponseDto(entrada);
    }

    public ProdutoEntradaResponseDto atualizar(UUID id, ProdutoEntradaRequestDto dto) {
        ProdutoEntrada entrada = entradaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entrada não encontrada"));

        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        entrada.setProduto(produto);
        entrada.setQuantidade(dto.quantidade());
        entrada.setDataEntrada(dto.dataEntrada());
        entrada.setFornecedor(dto.fornecedor());

        entradaRepository.save(entrada);
        return new ProdutoEntradaResponseDto(entrada);
    }
    
    public List<ProdutoEntradaResponseDto> listarComFiltros(LocalDate dataInicio, LocalDate dataFim, UUID produtoId) {
        try {
            List<ProdutoEntrada> entradas;
            
            if (dataInicio != null && dataFim != null && produtoId != null) {
                entradas = entradaRepository.findByDataEntradaBetweenAndProdutoId(dataInicio, dataFim, produtoId);
            } else if (dataInicio != null && dataFim != null) {
                entradas = entradaRepository.findByDataEntradaBetween(dataInicio, dataFim);
            } else if (produtoId != null) {
                entradas = entradaRepository.findByProdutoId(produtoId);
            } else if (dataInicio != null) {
                entradas = entradaRepository.findByDataEntradaGreaterThanEqual(dataInicio);
            } else if (dataFim != null) {
                entradas = entradaRepository.findByDataEntradaLessThanEqual(dataFim);
            } else {
                entradas = entradaRepository.findAll();
            }
            
            return entradas.stream()
                    .map(ProdutoEntradaResponseDto::new)
                    .collect(Collectors.toList());
                    
        } catch (Exception e) {
            throw new RuntimeException("Erro ao filtrar entradas: " + e.getMessage(), e);
        }
    }

    public void deletar(UUID id) {
        ProdutoEntrada entrada = entradaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entrada não encontrada"));

        entradaRepository.delete(entrada);
    }
}
