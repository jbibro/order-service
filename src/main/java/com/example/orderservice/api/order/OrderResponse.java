package com.example.orderservice.api.order;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class OrderResponse {
    Long id;
    String buyersEmail;
    List<Long> products;
    int totalPrice;
}
