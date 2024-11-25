package com.ibeus.Comanda.Digital;

import com.ibeus.Comanda.Digital.model.*;
import com.ibeus.Comanda.Digital.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ComandaService {
    private final ComandaRepository comandaRepository;
    private final ItemComandaRepository itemComandaRepository;
    private final ProdutoRepository produtoRepository;

    public ComandaService(ComandaRepository comandaRepository, 
                         ItemComandaRepository itemComandaRepository, 
                         ProdutoRepository produtoRepository) {
        this.comandaRepository = comandaRepository;
        this.itemComandaRepository = itemComandaRepository;
        this.produtoRepository = produtoRepository;
    }

    public Comanda abrirComanda(String mesa) {
        Comanda comanda = new Comanda();
        comanda.setMesa(mesa);
        comanda.setDataAbertura(LocalDateTime.now());
        comanda.setStatus(StatusComanda.ABERTA);
        comanda.setValorTotal(BigDecimal.ZERO);
        return comandaRepository.save(comanda);
    }

    public Comanda adicionarItem(Long comandaId, Long produtoId, Integer quantidade, String observacao) {
        Comanda comanda = comandaRepository.findById(comandaId)
            .orElseThrow(() -> new RuntimeException("Comanda não encontrada"));
        
        Produto produto = produtoRepository.findById(produtoId)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        ItemComanda item = new ItemComanda();
        item.setComanda(comanda);
        item.setProduto(produto);
        item.setQuantidade(quantidade);
        item.setPrecoUnitario(produto.getPreco());
        item.setSubtotal(produto.getPreco().multiply(BigDecimal.valueOf(quantidade)));
        item.setObservacao(observacao);

        comanda.getItens().add(item);
        comanda.setValorTotal(comanda.getValorTotal().add(item.getSubtotal()));
        
        return comandaRepository.save(comanda);
    }

    public Comanda fecharComanda(Long comandaId) {
        Comanda comanda = comandaRepository.findById(comandaId)
            .orElseThrow(() -> new RuntimeException("Comanda não encontrada"));
        
        comanda.setStatus(StatusComanda.FECHADA);
        comanda.setDataFechamento(LocalDateTime.now());
        
        return comandaRepository.save(comanda);
    }

    public List<Comanda> listarComandasAbertas() {
        return comandaRepository.findByStatus(StatusComanda.ABERTA);
    }

    // Novos métodos utilizando itemComandaRepository
    
    public void removerItem(Long comandaId, Long itemId) {
        Comanda comanda = comandaRepository.findById(comandaId)
            .orElseThrow(() -> new RuntimeException("Comanda não encontrada"));
            
        ItemComanda item = itemComandaRepository.findById(itemId)
            .orElseThrow(() -> new RuntimeException("Item não encontrado"));
            
        if (!item.getComanda().getId().equals(comandaId)) {
            throw new RuntimeException("Item não pertence a esta comanda");
        }
        
        comanda.setValorTotal(comanda.getValorTotal().subtract(item.getSubtotal()));
        comanda.getItens().remove(item);
        itemComandaRepository.delete(item);
        comandaRepository.save(comanda);
    }

    public ItemComanda atualizarQuantidadeItem(Long itemId, Integer novaQuantidade) {
        ItemComanda item = itemComandaRepository.findById(itemId)
            .orElseThrow(() -> new RuntimeException("Item não encontrado"));
            
        Comanda comanda = item.getComanda();
        
        // Atualiza o valor total da comanda
        comanda.setValorTotal(comanda.getValorTotal()
            .subtract(item.getSubtotal()) // Remove o valor antigo
            .add(item.getPrecoUnitario().multiply(BigDecimal.valueOf(novaQuantidade)))); // Adiciona o novo valor
            
        // Atualiza o item
        item.setQuantidade(novaQuantidade);
        item.setSubtotal(item.getPrecoUnitario().multiply(BigDecimal.valueOf(novaQuantidade)));
        
        itemComandaRepository.save(item);
        comandaRepository.save(comanda);
        
        return item;
    }

    public List<ItemComanda> listarItensPorComanda(Long comandaId) {
        return itemComandaRepository.findByComandaId(comandaId);
    }
}