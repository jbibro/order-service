package com.example.orderservice.api.order;

import com.example.orderservice.order.OrderRepository;
import com.example.orderservice.order.PlaceOrderUseCase;
import com.example.orderservice.product.Product;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@RestController
@AllArgsConstructor
public class OrderController {

    private final PlaceOrderUseCase placeOrderUseCase;
    private final OrderRepository orderRepository;

    @PostMapping("/orders")
    public void create(@RequestBody @Valid OrderRequest orderRequest) {
        placeOrderUseCase.execute(orderRequest);
    }

    @GetMapping("/orders")
    public List<OrderResponse> get(@RequestParam("start") @DateTimeFormat(iso = DATE) LocalDate from,
                                   @RequestParam("end") @DateTimeFormat(iso = DATE) LocalDate end) {
        return orderRepository.findAllByCreatedAtBetween(from.atStartOfDay(), end.atTime(LocalTime.MAX))
                .stream()
                .map(it -> OrderResponse
                        .builder()
                        .id(it.getId())
                        .buyersEmail(it.getBuyersEmail())
                        .products(it.getProducts().stream().map(Product::getId).collect(toList()))
                        .totalPrice(it.totalPrice())
                        .build()
                )
                .collect(toList());

    }
}
