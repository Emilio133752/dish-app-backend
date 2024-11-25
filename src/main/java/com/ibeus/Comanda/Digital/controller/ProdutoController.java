package com.ibeus.Comanda.Digital.controller;

import com.ibeus.Comanda.Digital.model.Produto;
import com.ibeus.Comanda.Digital.service.CategoriaService;
import com.ibeus.Comanda.Digital.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid; // Se estiver usando Jakarta
import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody Produto produto) {
        // Verifica se a categoria existe usando o ID
        if (produto.getCategoria() == null || !categoriaService.existsById(produto.getCategoria().getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(null); // Ou pode retornar um objeto de erro
        }

        try {
            Produto produtoSalvo = produtoService.salvar(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null); // Ou pode retornar um objeto de erro
        }
    }

    @PutMapping
    public ResponseEntity<Produto> atualizarProduto(@Valid @RequestBody Produto produto) {
        // Verifica se a categoria existe usando o ID
        if (produto.getCategoria() == null || !categoriaService.existsById(produto.getCategoria().getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(null); // Ou pode retornar um objeto de erro
        }

        try {
            Produto produtoSalvo = produtoService.atualizar(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null); // Ou pode retornar um objeto de erro
        }
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        Produto produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
