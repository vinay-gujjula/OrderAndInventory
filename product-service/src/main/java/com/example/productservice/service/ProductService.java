package com.example.productservice.service;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ProductDto> getProductById(Long id) {
        return productRepository.findById(id).map(this::toDto);
    }

    public ProductDto createProduct(ProductDto dto) {
        Product product = new Product(dto.getName(), dto.getAvailableQuantity());
        Product saved = productRepository.save(product);
        return toDto(saved);
    }

    private ProductDto toDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getAvailableQuantity());
    }
}