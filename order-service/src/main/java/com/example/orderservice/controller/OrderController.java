package com.example.orderservice.controller;

import com.example.orderservice.client.ProductClient;
import com.example.orderservice.dto.OrderRequestDto;
import com.example.orderservice.dto.OrderResponseDto;
import com.example.orderservice.dto.ProductDto;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private ProductClient productClient;

    @GetMapping
    public String getOrders() {
        return "List of orders";
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderRequestDto request) {
        ProductDto product;
        try {
            product = productClient.getProduct(request.getProductId());
        } catch (FeignException.NotFound e) {
            return ResponseEntity.badRequest().body(new OrderResponseDto(request.getProductId(), request.getQuantity(), "FAILED", "Product not found"));
        } catch (FeignException e) {
            return ResponseEntity.status(503).body(new OrderResponseDto(request.getProductId(), request.getQuantity(), "FAILED", "Product service unavailable"));
        }

        if (product == null) {
            return ResponseEntity.badRequest().body(new OrderResponseDto(request.getProductId(), request.getQuantity(), "FAILED", "Product not found"));
        }

        if (product.getAvailableQuantity() < request.getQuantity()) {
            return ResponseEntity.badRequest().body(new OrderResponseDto(request.getProductId(), request.getQuantity(), "FAILED", "Insufficient inventory"));
        }

        return ResponseEntity.ok(new OrderResponseDto(request.getProductId(), request.getQuantity(), "SUCCESS", "Order placed successfully"));
    }
}