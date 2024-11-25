package com.ibeus.Comanda.Digital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ibeus.Comanda.Digital.model.Order;
import com.ibeus.Comanda.Digital.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping()
    public List<Order> getAllOrders() {
        return orderService.findAll("");
    }

    @GetMapping("/by-email/{email}")
    public List<Order> getAllOrdersAuth(@PathVariable String email) {
        return orderService.findAll(email);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.findById(id, "");
    }

    @GetMapping("/{id}/{email}")
    public Order getOrderByIdAuth(@PathVariable Long id, @PathVariable String email) {
        return orderService.findById(id, email);
    }

    @GetMapping("/last-order/{email}")
    public Order getLastOrder(@PathVariable String email) {
        return orderService.lastOrder(email);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.create(order);
        savedOrder.setUser(null);
        return savedOrder;
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return orderService.update(id, order);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody String status) {
        Order updatedOrder = orderService.updateStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}