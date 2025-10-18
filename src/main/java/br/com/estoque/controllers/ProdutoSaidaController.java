package br.com.estoque.controllers;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dtos.ProdutoSaidaRequestDto;
import br.com.estoque.dtos.ProdutoSaidaResponseDto;
import br.com.estoque.services.ProdutoSaidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/saidas")
@Tag(name = "Saídas de Produto", description = "Gerenciamento de saídas de estoque")
public class ProdutoSaidaController {

    @Autowired
    private ProdutoSaidaService service;

    @Operation(summary = "Cadastro de uma saida de produto", description = "Cria uma nova saida de produto no banco de dados.")
    @PostMapping
    public ResponseEntity<ProdutoSaidaResponseDto> criar(@RequestBody @Valid ProdutoSaidaRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @Operation(summary = "Consulta todas saidas de produtos", description = "Retorna todas saidas de produtos cadastrados no banco de dados.")
    @GetMapping
    public ResponseEntity<List<ProdutoSaidaResponseDto>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Consulta de saida de produto pelo ID", description = "Retorna uma saida de produto específico pelo ID.")
    @GetMapping("{id}")
    public ResponseEntity<ProdutoSaidaResponseDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Edição de saida de produto", description = "Atualiza os dados de uma saida de produto no banco de dados.")
    @PutMapping("{id}")
    public ResponseEntity<ProdutoSaidaResponseDto> atualizar(@PathVariable UUID id, @RequestBody @Valid ProdutoSaidaRequestDto dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }
    
    @Operation(summary = "Consulta saida com filtros", description = "Retorna saidas filtradas por data e produto")
    @GetMapping("/filtro")
    public ResponseEntity<List<ProdutoSaidaResponseDto>> listarComFiltro(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio, 
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            @RequestParam(required = false) UUID produtoId) {
        
        System.out.println("📥 Filtros recebidos - DataInicio: " + dataInicio + 
                          ", DataFim: " + dataFim + 
                          ", ProdutoId: " + produtoId);
        
        return ResponseEntity.ok(service.listarComFiltros(dataInicio, dataFim, produtoId));
    }
    
    @Operation(summary = "Exclusão de uma saida de produto", description = "Exclui uma saida de produto no banco de dados.")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

