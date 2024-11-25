package com.ibeus.Comanda.Digital.repository;

import com.ibeus.Comanda.Digital.model.ItemComanda;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemComandaRepository extends JpaRepository<ItemComanda, Long> {
    List<ItemComanda> findByComandaId(Long comandaId);
}