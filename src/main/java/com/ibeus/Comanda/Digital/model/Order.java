package com.ibeus.Comanda.Digital.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "order_dishes",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> dishes;

    private Double totalAmount;

    private String address;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "payment_method")
    private String paymentMethod;

    private String status = "pendente";

    private String orderDate;
}