package com.ibeus.Comanda.Digital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ibeus.Comanda.Digital.model.Dish;
import com.ibeus.Comanda.Digital.model.Order;
import com.ibeus.Comanda.Digital.model.User;
import com.ibeus.Comanda.Digital.repository.DishRepository;
import com.ibeus.Comanda.Digital.repository.OrderRepository;
import com.ibeus.Comanda.Digital.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Order> findAll(String email) {
        if (email != null && !email.isEmpty()) {
            // Busca o usuário pelo e-mail
            User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + email));
            
            if ("entregador".equalsIgnoreCase(user.getRole())) {
                // Retorna os pedidos com status READY
                return orderRepository.findAllByStatus("READY", Sort.by(Sort.Order.desc("orderDate")));
            } else if ("cliente".equalsIgnoreCase(user.getRole())) {
                // Retorna os pedidos com status DELIVERY e associados ao usuário
                return orderRepository.findAllByStatusAndUser("DELIVERED", user, Sort.by(Sort.Order.desc("orderDate")));
            }
        }
    
        // Caso o email seja vazio, retorna todos os pedidos sem filtros
        return orderRepository.findAll(Sort.by(Sort.Order.desc("orderDate")));
    }

    public Order findById(Long id, String email) {
        if (email != null && !email.isEmpty()) {
            User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + email));
        
            return orderRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Order não encontrado para o usuário e ID fornecidos."));
        }
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order lastOrder(String email) {
        if (email != null && !email.isEmpty()) {
            User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + email));
        
            return orderRepository.findFirstByUserOrderByOrderDateDesc(user)
                .orElseThrow(() -> new RuntimeException("Order não encontrado para o usuário fornecidos."));
        }
        throw new RuntimeException("Order not found");
    }

    public Order updateStatus(Long id, String status) {
        Order order = findById(id, "");
        // Verifica se o status é válido
        if (status == null || status.isEmpty()) {
            throw new RuntimeException("Status inválido");
        }
        order.setStatus(status);
        return orderRepository.save(order);
    }

    @Transactional
    public Order create(Order order) {
        List<Long> dishIds = order.getDishes().stream()
            .map(Dish::getId)
            .collect(Collectors.toList());

        List<Dish> dishes = dishRepository.findAllById(dishIds);

        if (dishes.size() != dishIds.size()) {
            throw new RuntimeException("Alguns pratos não foram encontrados.");
        }

        order.setDishes(dishes);

        User user = userRepository.findByEmail(order.getUser().getEmail())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + order.getUser().getEmail()));

        order.setUser(user);

        return orderRepository.save(order);
    }

    public Order update(Long id, Order orderDetails) {
        Order order = findById(id, "");
        List<Dish> dishes = orderDetails.getDishes().stream()
            .map(dish -> dishRepository.findById(dish.getId())
                .orElseThrow(() -> new RuntimeException("Dish not found: " + dish.getId())))
            .collect(Collectors.toList());

        order.setUser(orderDetails.getUser());
        order.setDishes(dishes);
        order.setTotalAmount(orderDetails.getTotalAmount());
        order.setStatus(orderDetails.getStatus());
        order.setOrderDate(orderDetails.getOrderDate());
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        Order order = findById(id, "");
        orderRepository.delete(order);
    }
}