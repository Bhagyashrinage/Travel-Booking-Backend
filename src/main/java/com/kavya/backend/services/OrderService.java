package com.kavya.backend.services;

import com.kavya.backend.entities.Order;
import com.kavya.backend.entities.OrderItem;
import com.kavya.backend.repositories.OrderItemRepository;
import com.kavya.backend.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired private OrderItemRepository orderItemRepository;

    public Order placeOrder(Long userId, List<OrderItem> items) {
        double total = items.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();

        Order order = new Order();
        order.setUserId(userId);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");
        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);

        for (OrderItem item : items) {
            item.setOrderId(savedOrder.getId());
            orderItemRepository.save(item);
        }

        return savedOrder;
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
