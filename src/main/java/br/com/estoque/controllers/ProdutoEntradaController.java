package br.com.estoque.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dtos.ProdutoEntradaRequestDto;
import br.com.estoque.dtos.ProdutoEntradaResponseDto;
import br.com.estoque.services.ProdutoEntradaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/entradas")
@Tag(name = "Entradas de Produto", description = "Gerenciamento de entradas de estoque")
public class ProdutoEntradaController {

    @Autowired
    private ProdutoEntradaService service;

    @PostMapping
    public ResponseEntity<ProdutoEntradaResponseDto> post(@RequestBody @Valid ProdutoEntradaRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoEntradaResponseDto>> get() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoEntradaResponseDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProdutoEntradaResponseDto> put(
            @PathVariable UUID id,
            @RequestBody  @Valid ProdutoEntradaRequestDto dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
