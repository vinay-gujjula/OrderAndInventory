package com.example.orderservice.dto;

public class ProductDto {

    private Long id;
    private String name;
    private int availableQuantity;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, int availableQuantity) {
        this.id = id;
        this.name = name;
        this.availableQuantity = availableQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}