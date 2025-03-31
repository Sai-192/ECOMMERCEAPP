package com.srikanth.inventoryManagement.kafka;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Simple POJO that matches the order message structure
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderEvent {
    private String productName;
    private int quantity;

    // Getters and setters
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
