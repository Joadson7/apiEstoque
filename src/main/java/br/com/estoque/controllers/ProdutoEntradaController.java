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

import br.com.estoque.dtos.ProdutoEntradaRequestDto;
import br.com.estoque.dtos.ProdutoEntradaResponseDto;
import br.com.estoque.services.ProdutoEntradaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/entradas")
@Tag(name = "Entradas de Produto", description = "Gerenciamento de entradas de estoque")
public class ProdutoEntradaController {

    @Autowired
    private ProdutoEntradaService service;

    @Operation(summary = "Cadastro de uma entrada de produto", description = "Cria uma nova entrada de produto no banco de dados.")
    @PostMapping
    public ResponseEntity<ProdutoEntradaResponseDto> post(@RequestBody @Valid ProdutoEntradaRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @Operation(summary = "Consulta todas entradas de produtos", description = "Retorna todas entradas de produtos cadastrados no banco de dados.")
    @GetMapping
    public ResponseEntity<List<ProdutoEntradaResponseDto>> get() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Consulta de entrada de produto pelo ID", description = "Retorna uma entrada de produto específico pelo ID.")
    @GetMapping("{id}")
    public ResponseEntity<ProdutoEntradaResponseDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Edição de entrada de produto", description = "Atualiza os dados de uma entrada de produto no banco de dados.")
    @PutMapping("{id}")
    public ResponseEntity<ProdutoEntradaResponseDto> put(
            @PathVariable UUID id,
            @RequestBody  @Valid ProdutoEntradaRequestDto dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }
    
    @Operation(summary = "Consulta entradas com filtros", description = "Retorna entradas filtradas por data e produto")
    @GetMapping("/filtro")
    public ResponseEntity<List<ProdutoEntradaResponseDto>> getComFiltros(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            @RequestParam(required = false) UUID produtoId) {
        
        return ResponseEntity.ok(service.listarComFiltros(dataInicio, dataFim, produtoId));
    }

    @Operation(summary = "Exclusão de uma entrada de produto", description = "Exclui uma entrada de produto no banco de dados.")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
