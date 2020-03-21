package com.example.orderservice.order;

import com.example.orderservice.api.order.OrderRequest;
import com.example.orderservice.product.ProductRepository;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static java.util.Collections.emptyList;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.*;

class PlaceOrderUseCaseTest {

    OrderRepository orderRepository = mock(OrderRepository.class);
    ProductRepository productRepository = mock(ProductRepository.class);

    PlaceOrderUseCase placeOrderUseCase = new PlaceOrderUseCase(orderRepository, productRepository);

    @Test
    void shouldNotCreateOrderWithoutProducts() {
        // given
        when(productRepository.findAllByIdInAndActiveTrue(anyCollection())).thenReturn(emptyList());

        // when
        placeOrderUseCase.execute(new OrderRequest("buyer", Set.of(1L, 2L)));

        // then
        verifyNoInteractions(orderRepository);
    }
}