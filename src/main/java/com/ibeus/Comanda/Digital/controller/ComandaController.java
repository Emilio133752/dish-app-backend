package com.ibeus.Comanda.Digital.controller;

import com.ibeus.Comanda.Digital.model.Comanda;
import com.ibeus.Comanda.Digital.model.ItemComanda;
import com.ibeus.Comanda.Digital.service.ComandaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comandas")
public class ComandaController {

    private final ComandaService comandaService;

    public ComandaController(ComandaService comandaService) {
        this.comandaService = comandaService;
    }

    @PostMapping
    public ResponseEntity<Comanda> abrirComanda(@RequestParam String mesa) {
        return ResponseEntity.ok(comandaService.abrirComanda(mesa));
    }

    @PostMapping("/{comandaId}/itens")
    public ResponseEntity<Comanda> adicionarItem(
            @PathVariable Long comandaId,
            @RequestParam Long produtoId,
            @RequestParam Integer quantidade,
            @RequestParam(required = false) String observacao) {
        return ResponseEntity.ok(comandaService.adicionarItem(comandaId, produtoId, quantidade, observacao));
    }

    @DeleteMapping("/{comandaId}/itens/{itemId}")
    public ResponseEntity<Void> removerItem(
            @PathVariable Long comandaId,
            @PathVariable Long itemId) {
        comandaService.removerItem(comandaId, itemId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/itens/{itemId}/quantidade")
    public ResponseEntity<ItemComanda> atualizarQuantidadeItem(
            @PathVariable Long itemId,
            @RequestParam Integer quantidade) {
        return ResponseEntity.ok(comandaService.atualizarQuantidadeItem(itemId, quantidade));
    }

    @PostMapping("/{comandaId}/fechar")
    public ResponseEntity<Comanda> fecharComanda(@PathVariable Long comandaId) {
        return ResponseEntity.ok(comandaService.fecharComanda(comandaId));
    }

    @GetMapping
    public ResponseEntity<List<Comanda>> listarComandasAbertas() {
        return ResponseEntity.ok(comandaService.listarComandasAbertas());
    }

    @GetMapping("/{comandaId}/itens")
    public ResponseEntity<List<ItemComanda>> listarItensPorComanda(@PathVariable Long comandaId) {
        return ResponseEntity.ok(comandaService.listarItensPorComanda(comandaId));
    }
}