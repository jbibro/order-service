package com.example.orderservice.api.product;

import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Value
public class ProductRequest {
    @NotEmpty
    String name;
    @Min(1)
    int price;
}
