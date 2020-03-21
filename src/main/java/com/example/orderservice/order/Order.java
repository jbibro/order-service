package com.example.orderservice.order;

import com.example.orderservice.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;

@Table(name = "orders")
@Entity
@NoArgsConstructor
@Getter
public class Order {
    @Id @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    private String buyersEmail;
    private LocalDateTime createdAt = now();

    @Builder
    public Order(String buyersEmail, List<Product> products) {
        this.buyersEmail = buyersEmail;
        this.products = products;
    }

    public int totalPrice() {
        return products.stream()
                .mapToInt(Product::getPrice)
                .sum();
    }
}
