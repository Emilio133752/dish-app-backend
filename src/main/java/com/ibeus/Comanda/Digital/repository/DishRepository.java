package com.ibeus.Comanda.Digital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ibeus.Comanda.Digital.model.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {
}