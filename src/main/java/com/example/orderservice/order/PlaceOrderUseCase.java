package com.example.orderservice.order;

import com.example.orderservice.api.order.OrderRequest;
import com.example.orderservice.product.Product;
import com.example.orderservice.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
public class PlaceOrderUseCase {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void execute(OrderRequest orderRequest) {
        List<Product> products = productRepository.findAllByIdInAndActiveTrue(orderRequest.getProducts());
        if (!products.isEmpty()) {
            save(orderRequest, products);
        }
    }

    private void save(OrderRequest orderRequest, List<Product> products) {
        orderRepository.save(
                Order
                        .builder()
                        .buyersEmail(orderRequest.getBuyersEmail())
                        .products(products)
                        .build()
        );
    }


}
