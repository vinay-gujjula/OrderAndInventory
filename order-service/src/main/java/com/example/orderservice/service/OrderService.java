package com.example.orderservice.service;

import com.example.orderservice.client.ProductClient;
import com.example.orderservice.dto.OrderRequestDto;
import com.example.orderservice.dto.OrderResponseDto;
import com.example.orderservice.dto.ProductDto;
import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.repository.OrderRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderRepository orderRepository;

    public OrderResponseDto placeOrder(OrderRequestDto request) {
        ProductDto product;
        try {
            product = productClient.getProduct(request.getProductId());
        } catch (FeignException.NotFound e) {
            OrderEntity failed = orderRepository.save(new OrderEntity(request.getProductId(), request.getQuantity(), "FAILED", "Product not found"));
            return toResponseDto(failed);
        } catch (FeignException e) {
            OrderEntity failed = orderRepository.save(new OrderEntity(request.getProductId(), request.getQuantity(), "FAILED", "Product service unavailable"));
            return toResponseDto(failed);
        }

        if (product == null) {
            OrderEntity failed = orderRepository.save(new OrderEntity(request.getProductId(), request.getQuantity(), "FAILED", "Product not found"));
            return toResponseDto(failed);
        }

        if (product.getAvailableQuantity() < request.getQuantity()) {
            OrderEntity failed = orderRepository.save(new OrderEntity(request.getProductId(), request.getQuantity(), "FAILED", "Insufficient inventory"));
            return toResponseDto(failed);
        }

        OrderEntity order = new OrderEntity(request.getProductId(), request.getQuantity(), "SUCCESS", "Order placed successfully");
        return toResponseDto(orderRepository.save(order));
    }

    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    private OrderResponseDto toResponseDto(OrderEntity entity) {
        return new OrderResponseDto(entity.getProductId(), entity.getQuantity(), entity.getStatus(), entity.getMessage());
    }
}