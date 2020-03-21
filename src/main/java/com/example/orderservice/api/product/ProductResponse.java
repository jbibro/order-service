package com.example.orderservice.api.product;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class ProductResponse {
    Long id;
    String sku;
    String name;
    int price;
    boolean active;
    LocalDateTime createdAt;
}
