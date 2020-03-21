package com.example.orderservice.api.product;

import com.example.orderservice.product.Product;
import com.example.orderservice.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductRepository repository;

    @PostMapping("/products")
    public void create(@RequestBody @Valid ProductRequest productRequest) {
        repository.save(
                Product
                        .builder()
                        .name(productRequest.getName())
                        .price(productRequest.getPrice())
                        .build()
        );
    }

    @GetMapping("/products")
    public List<ProductResponse> get() {
        return repository.findAll()
                .stream()
                .map(it -> ProductResponse
                        .builder()
                        .id(it.getId())
                        .sku(it.getSku())
                        .name(it.getName())
                        .active(it.isActive())
                        .price(it.getPrice())
                        .createdAt(it.getCreatedAt())
                        .build()
                )
                .collect(toList());
    }

    @PutMapping("/products/{productId}")
    @Transactional
    public void update(@PathVariable("productId") Long productId,
                       @RequestBody @Valid ProductRequest productRequest) {
        repository.findById(productId).ifPresent(
                it -> {
                    it.rename(productRequest.getName());
                    it.changePrice(productRequest.getPrice());
                }
        );
    }

    @DeleteMapping("/products/{productId}")
    @Transactional
    public void delete(@PathVariable("productId") Long productId) {
        repository.findById(productId).ifPresent(Product::delete);
    }
}
