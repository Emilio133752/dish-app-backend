package com.ibeus.Comanda.Digital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibeus.Comanda.Digital.model.Dish;
import com.ibeus.Comanda.Digital.repository.DishRepository;

import java.util.List;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

    public Dish findById(Long id) {
        return dishRepository.findById(id).orElseThrow(() -> new RuntimeException("Dish not found"));
    }

    public Dish create(Dish dish) {
        return dishRepository.save(dish);
    }

    public Dish update(Long id, Dish dishDetails) {
        Dish dish = findById(id);
        dish.setName(dishDetails.getName());
        dish.setDescription(dishDetails.getDescription());
        dish.setPrice(dishDetails.getPrice());
        return dishRepository.save(dish);
    }

    public void delete(Long id) {
        Dish dish = findById(id);
        dishRepository.delete(dish);
    }
}