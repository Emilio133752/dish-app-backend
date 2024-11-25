package com.ibeus.Comanda.Digital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ibeus.Comanda.Digital.model.Dish;
import com.ibeus.Comanda.Digital.service.DishService;

import java.util.List;

@RestController
@RequestMapping("/dishes")
@CrossOrigin(origins = "http://localhost:4200")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping
    public List<Dish> getAllDishes() {
        return dishService.findAll();
    }

    @GetMapping("/{id}")
    public Dish getDishById(@PathVariable Long id) {
        return dishService.findById(id);
    }

    @PostMapping
    public Dish createDish(@RequestBody Dish dish) {
        return dishService.create(dish);
    }

    @PutMapping("/{id}")
    public Dish updateDish(@PathVariable Long id, @RequestBody Dish dish) {
        return dishService.update(id, dish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return ResponseEntity.noContent().build();
    }
}