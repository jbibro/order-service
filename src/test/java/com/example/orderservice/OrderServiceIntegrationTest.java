package com.example.orderservice;

import com.example.orderservice.order.Order;
import com.example.orderservice.order.OrderRepository;
import com.example.orderservice.product.Product;
import com.example.orderservice.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;

import static java.net.http.HttpResponse.BodyHandlers.discarding;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class OrderServiceIntegrationTest {

    @LocalServerPort
    int port;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    private HttpClient httpClient = HttpClient.newHttpClient();

    @Test
    void placeOrderTest() throws IOException, InterruptedException {
        // given a product
        productRepository.save(Product
                .builder()
                .name("p1")
                .price(10)
                .build()
        );

        // when I place order
        httpClient.send(
                HttpRequest
                        .newBuilder()
                        .uri(URI.create("http://localhost:" + port + "/orders"))
                        .POST(HttpRequest.BodyPublishers.ofString(
                                "{\"buyersEmail\": \"aaa\",\"products\": [1] }"
                        ))
                        .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .build(),
                discarding()
        );

        // then order should be found
        List<Order> orders = orderRepository.findAll();
        assertThat(orders).hasSize(1);
    }
}
