package com.eight.order.module.service.impl;

import com.eight.order.module.model.Order;
import com.eight.order.module.repository.IOrderRepo;
import com.eight.order.module.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final IOrderRepo orderRepo;

    public OrderServiceImpl(IOrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Optional<Order> getOrderById(Integer orderId) {
        return orderRepo.findById(orderId);
    }

}
