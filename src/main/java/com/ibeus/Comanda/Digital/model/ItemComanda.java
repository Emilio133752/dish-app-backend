package com.ibeus.Comanda.Digital.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "itens_comanda")
public class ItemComanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "comanda_id", nullable = false)
    private Comanda comanda;
    
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
    
    @Column(nullable = false)
    private Integer quantidade;
    
    @Column(nullable = false)
    private BigDecimal precoUnitario;
    
    @Column(nullable = false)
    private BigDecimal subtotal;
    
    private String observacao;
}