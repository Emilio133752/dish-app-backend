package com.ibeus.Comanda.Digital.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ibeus.Comanda.Digital.model.Order;
import com.ibeus.Comanda.Digital.model.User;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByIdAndUser(Long id, User user);
    List<Order> findAllByStatus(String status, Sort sort);

    List<Order> findAllByStatusAndUser(String status, User user, Sort sort);

    Optional<Order> findFirstByUserOrderByOrderDateDesc(User user);
}