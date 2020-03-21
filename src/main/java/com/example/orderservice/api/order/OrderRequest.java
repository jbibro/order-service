package com.example.orderservice.api.order;

import lombok.Value;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Value
public class OrderRequest {
    @NotEmpty
    String buyersEmail;
    @NotEmpty
    Set<Long> products;
}
