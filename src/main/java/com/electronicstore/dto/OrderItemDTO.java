package com.electronicstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemDTO {

    private Long productId;

    @NotNull(message = "Product ID is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    // Getters and setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
