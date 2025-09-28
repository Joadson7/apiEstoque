package br.com.estoque.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/v1/enums")
@Tag(name = "Categorias", description = "Categorias cadastradas")
public class EnumController {

	@GetMapping("/categorias-produtos")
	@ResponseBody
    public ResponseEntity<Map<String, String>> getCategoriasProduto() {
        Map<String, String> categorias = new LinkedHashMap<>();
        
        // Mapeamento manual para garantir os valores corretos
        categorias.put("HIDRÁULICA", "Hidráulica");
        categorias.put("METAL", "Metal");
        categorias.put("MADEIRA", "Madeira");
        categorias.put("ELÉTRICO", "Elétrico");
        categorias.put("FERRAMENTAS", "Ferramentas");
        categorias.put("ESCRITÓRIO", "Escritório");
        
        return ResponseEntity.ok(categorias);
    }
}