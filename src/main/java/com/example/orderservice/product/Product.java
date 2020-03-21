package com.example.orderservice.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.LocalDateTime.now;

@Table(name = "products")
@Entity
@Getter
@NoArgsConstructor
public class Product {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String sku = UUID.randomUUID().toString();
    private int price;
    private boolean active = true;
    private LocalDateTime createdAt = now();

    @Builder
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void rename(String newName) {
        name = newName;
    }

    public void changePrice(int newPrice) {
        price = newPrice;
    }

    public void delete() {
        active = false;
    }
}
