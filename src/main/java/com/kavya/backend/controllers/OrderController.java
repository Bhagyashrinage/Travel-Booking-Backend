package com.kavya.backend.controllers;

import com.kavya.backend.dto.OrderRequest;
import com.kavya.backend.entities.Order;
import com.kavya.backend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest, Principal principal) {
        Long userId = orderRequest.getUserId(); // later extract from JWT
        Order order = orderService.placeOrder(userId, orderRequest.getItems());
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{userId}")
    public List<Order> getOrdersByUser(@PathVariable Long userId) {
        return orderService.getOrdersByUser(userId);
    }
}

