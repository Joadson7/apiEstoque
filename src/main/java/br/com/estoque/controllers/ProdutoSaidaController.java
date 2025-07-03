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

import br.com.estoque.dtos.ProdutoSaidaRequestDto;
import br.com.estoque.dtos.ProdutoSaidaResponseDto;
import br.com.estoque.services.ProdutoSaidaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/saidas")
@Tag(name = "Saídas de Produto", description = "Gerenciamento de saídas de estoque")
public class ProdutoSaidaController {

    @Autowired
    private ProdutoSaidaService service;

    @PostMapping
    public ResponseEntity<ProdutoSaidaResponseDto> criar(@RequestBody @Valid ProdutoSaidaRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoSaidaResponseDto>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoSaidaResponseDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProdutoSaidaResponseDto> atualizar(@PathVariable UUID id, @RequestBody @Valid ProdutoSaidaRequestDto dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

