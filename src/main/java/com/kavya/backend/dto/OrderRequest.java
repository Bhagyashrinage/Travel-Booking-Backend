package com.kavya.backend.dto;

import com.kavya.backend.entities.OrderItem;

import java.util.List;

public class OrderRequest {

    private Long userId; // optional if you get user from JWT
    private List<OrderItem> items;

    public OrderRequest() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
