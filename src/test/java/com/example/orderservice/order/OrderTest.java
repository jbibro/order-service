package com.example.orderservice.order;

import com.example.orderservice.product.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    @Test
    void totalPricePrice() {
        // given
        int firstPrice = 10;
        int secondPrice = 20;
        Order order = Order.builder()
                .products(List.of(
                        product(firstPrice),
                        product(secondPrice)
                ))
                .build();

        // then
        assertEquals(30, order.totalPrice());
    }

    private Product product(int price) {
        return Product
                .builder()
                .price(price)
                .build();
    }
}