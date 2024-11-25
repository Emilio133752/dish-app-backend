package com.ibeus.Comanda.Digital.repository;

import com.ibeus.Comanda.Digital.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
