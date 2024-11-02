package com.example.booking.service;

import com.example.booking.model.Order;
import com.example.booking.model.User;
import com.example.booking.repository.OrderRepository;
import com.example.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order save(Order order) {
        // Загрузите пользователя из базы данных, чтобы он стал управляемым
        User managedUser = userRepository.findById(order.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(managedUser);

        return orderRepository.save(order);
    }

    public Order update(Long id, Order updatedOrder) {
        Order existingOrder = findById(id);

        // Загрузите управляемого пользователя, чтобы избежать ошибок при обновлении
        if (updatedOrder.getUser() != null && updatedOrder.getUser().getId() != null) {
            User managedUser = userRepository.findById(updatedOrder.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            existingOrder.setUser(managedUser);
        }

        existingOrder.setOrderDate(updatedOrder.getOrderDate());
        existingOrder.setTotalAmount(updatedOrder.getTotalAmount());

        return orderRepository.save(existingOrder);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
