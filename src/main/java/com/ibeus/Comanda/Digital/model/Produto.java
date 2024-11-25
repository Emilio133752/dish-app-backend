package com.ibeus.Comanda.Digital.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do produto não pode estar vazio.")
    private String nome;

    @NotNull(message = "O preço do produto não pode ser nulo.")
    @Positive(message = "O preço do produto deve ser um valor positivo.")
    private BigDecimal preco;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false) // A categoria é obrigatória
    @NotNull(message = "A categoria do produto não pode ser nula.")
    private Categoria categoria;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // Método para obter o ID da categoria
    public Long getCategoriaId() {
        return (categoria != null) ? categoria.getId() : null;
    }
}
