package com.ibeus.Comanda.Digital.service;

import com.ibeus.Comanda.Digital.model.Categoria;
import com.ibeus.Comanda.Digital.model.Produto;
import com.ibeus.Comanda.Digital.model.User;
import com.ibeus.Comanda.Digital.repository.CategoriaRepository;
import com.ibeus.Comanda.Digital.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException; // Importação correta

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public Categoria create(Categoria categ) {
        return categoriaRepository.save(categ);
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID da categoria não pode ser nulo."); // Verifica se o ID é nulo
        }

        return categoriaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com ID: " + id)); // Lança exceção se não encontrado
    }

    public Categoria update(Long id, Categoria userDetails) {
        Categoria categ = findById(id);
        categ.setNome(userDetails.getNome());
        return categoriaRepository.save(categ);
    }

    public boolean existsById(Long id) {
        return id != null && categoriaRepository.existsById(id);
    }

    public void delete(Long id) {
        Categoria categ = findById(id);

        // Verifica se existem produtos vinculados à categoria
        List<Produto> produtos = produtoRepository.findByCategoria(categ); // Obtém os produtos vinculados à categoria
        if (!produtos.isEmpty()) { // Se a lista de produtos não estiver vazia, significa que a categoria tem produtos vinculados
            throw new IllegalStateException("Não é possível excluir a categoria, pois ela possui produtos vinculados.");
        }

        categoriaRepository.delete(categ); // Deleta a categoria se não houver produtos
    }
}
