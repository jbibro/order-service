package com.example.orderservice.order;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "products")
    List<Order> findAllByCreatedAtBetween(LocalDateTime from, LocalDateTime end);
}
