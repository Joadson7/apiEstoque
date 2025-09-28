package br.com.estoque.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoque.dtos.ProdutoSaidaRequestDto;
import br.com.estoque.dtos.ProdutoSaidaResponseDto;
import br.com.estoque.entities.Produto;
import br.com.estoque.entities.ProdutoSaida;
import br.com.estoque.repositories.ProdutoRepository;
import br.com.estoque.repositories.ProdutoSaidaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoSaidaService {

    @Autowired
    private ProdutoSaidaRepository saidaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoSaidaResponseDto criar(ProdutoSaidaRequestDto dto) {
        Produto produto = produtoRepository.findById(dto.produtoId())
            .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        int estoqueAtual = produto.getEstoqueAtual() != null ? produto.getEstoqueAtual() : 0;

        if (estoqueAtual < dto.quantidade()) {
            throw new IllegalArgumentException("Estoque insuficiente para saída.");
        }

        ProdutoSaida saida = new ProdutoSaida();
        saida.setProduto(produto);
        saida.setQuantidade(dto.quantidade());
        saida.setDataSaida(dto.dataSaida());
        saida.setDestino(dto.destino());
        saida.setRuasEmObra(dto.ruasEmObra());

        // Atualiza o estoque
        produto.setEstoqueAtual(estoqueAtual - dto.quantidade());

        // Verifica se o estoque não fica negativo
        saidaRepository.save(saida);
        
        produtoRepository.save(produto);
       

        return new ProdutoSaidaResponseDto(saida);
    }


    public List<ProdutoSaidaResponseDto> listarTodos() {
        return saidaRepository.findAll()
            .stream()
            .map(ProdutoSaidaResponseDto::new)
            .collect(Collectors.toList());
    }

    public ProdutoSaidaResponseDto buscarPorId(UUID id) {
        ProdutoSaida saida = saidaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Saída não encontrada"));
        return new ProdutoSaidaResponseDto(saida);
    }

    public ProdutoSaidaResponseDto atualizar(UUID id, ProdutoSaidaRequestDto dto) {
        ProdutoSaida saida = saidaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Saída não encontrada"));

        Produto produto = produtoRepository.findById(dto.produtoId())
            .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        saida.setProduto(produto);
        saida.setQuantidade(dto.quantidade());
        saida.setDataSaida(dto.dataSaida());
        saida.setDestino(dto.destino());
		saida.setRuasEmObra(dto.ruasEmObra());

		// Atualiza o estoque

        saidaRepository.save(saida);
        return new ProdutoSaidaResponseDto(saida);
    }

    public void deletar(UUID id) {
        ProdutoSaida saida = saidaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Saída não encontrada"));
        saidaRepository.delete(saida);
    }
}
