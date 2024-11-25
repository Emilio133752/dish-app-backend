package com.ibeus.Comanda.Digital.repository;

import com.ibeus.Comanda.Digital.model.Comanda;
import com.ibeus.Comanda.Digital.model.StatusComanda;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ComandaRepository extends JpaRepository<Comanda, Long> {
    List<Comanda> findByStatus(StatusComanda status);
    List<Comanda> findByDataAberturaGreaterThanEqual(LocalDateTime data);
}