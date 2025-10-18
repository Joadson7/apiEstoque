package br.com.estoque.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dtos.ProdutoRequestDto;
import br.com.estoque.dtos.ProdutoResponseDto;
import br.com.estoque.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/produtos")
@Tag(name = "Produtos", description = "Gerenciamento de produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;
    
    @Operation(summary = "Cadastro de produto", description = "Cria um novo produto no banco de dados.")
    @PostMapping
    public ResponseEntity<ProdutoResponseDto> criar(@RequestBody @Valid ProdutoRequestDto dto) {
        ProdutoResponseDto criado = produtoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @Operation(summary = "Consulta de produtos", description = "Retorna todos os produtos cadastrados no banco de dados.")
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @Operation(summary = "Consulta de produtos pelo ID", description = "Retorna um produto específico pelo ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @Operation(summary = "Edição de produto", description = "Atualiza os dados de um produto no banco de dados.")
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> atualizar(@PathVariable UUID id, @RequestBody @Valid ProdutoRequestDto dto) {
        return ResponseEntity.ok(produtoService.atualizar(id, dto));
    }

    @Operation(summary = "Desativar o produto", description = "Desativa um produto no banco de dados.")
    @PatchMapping("/{id}/desativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desativarProduto(@PathVariable UUID id) {
        produtoService.desativar(id);
    }
    
    @Operation(summary = "Consulta de produtos ativos", description = "Retorna apenas os produtos ativos.")
    @GetMapping("/ativos")
    public ResponseEntity<List<ProdutoResponseDto>> listarAtivos() {
        return ResponseEntity.ok(produtoService.listarAtivos());
    }
    
    
    @Operation(summary = "Consulta de produtos com filtros", description = "Retorna produtos filtrados por nome, categoria, etc.")
    @GetMapping("/filtrados")
    public ResponseEntity<List<ProdutoResponseDto>> listarComFiltros(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String dataInicio,
            @RequestParam(required = false) String dataFim) {
        
        List<ProdutoResponseDto> produtosFiltrados = produtoService.listarComFiltros(nome, categoria, dataInicio, dataFim);
        return ResponseEntity.ok(produtosFiltrados);
    }
}
