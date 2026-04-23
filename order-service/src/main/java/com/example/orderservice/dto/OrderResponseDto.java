package com.example.orderservice.dto;

public class OrderResponseDto {

    private Long productId;
    private int quantity;
    private String status;
    private String message;

    public OrderResponseDto() {
    }

    public OrderResponseDto(Long productId, int quantity, String status, String message) {
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
        this.message = message;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}