package com.ibeus.Comanda.Digital.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "comandas")
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String mesa;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataAbertura;
    
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataFechamento;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusComanda status;
    
    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL)
    private List<ItemComanda> itens = new ArrayList<>();
    
    @Column(nullable = false)
    private BigDecimal valorTotal;
}