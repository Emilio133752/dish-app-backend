package com.ibeus.Comanda.Digital.service;

import com.ibeus.Comanda.Digital.model.Categoria;
import com.ibeus.Comanda.Digital.model.Produto;
import com.ibeus.Comanda.Digital.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityNotFoundException; // Importação correta
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaService categoriaService; // Serviço para categoria

    @Transactional
    public Produto salvar(Produto produto) {
        // Verifica se a categoria existe
        if (produto.getCategoria() == null || produto.getCategoria().getId() == null) {
            throw new RuntimeException("A categoria deve ser informada.");
        }

        // Busca a categoria pelo ID
        Categoria categoria = categoriaService.findById(produto.getCategoria().getId());
        produto.setCategoria(categoria); // Define a categoria no produto

        return produtoRepository.save(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll(Sort.by(Sort.Order.desc("id")));
    }

    public Produto buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID do produto não pode ser nulo."); // Verifica se o ID é nulo
        }

        return produtoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + id)); // Lança exceção se não encontrado
    }

    @Transactional
    public Produto atualizar(Produto produto) {
        Produto produtoExistente = buscarPorId(produto.getId());

        // Atualiza os campos do produto existente
        produtoExistente.setNome(produto.getNome());
        produtoExistente.setPreco(produto.getPreco());
        produtoExistente.setDescricao(produto.getDescricao());

        // Atualiza a categoria se fornecida
        if (produto.getCategoria() != null) {
            Categoria categoria = categoriaService.findById(produto.getCategoria().getId());
            produtoExistente.setCategoria(categoria);
        }

        return produtoRepository.save(produtoExistente);
    }

    @Transactional
    public void deletar(Long id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }
}
