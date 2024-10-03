package com.eight.order.module.service.impl;

import com.eight.order.module.model.Order;
import com.eight.order.module.model.OrderTO;
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

    @Override
    public Optional<Order> getOrderById(String orderId) {
        return orderRepo.findById(orderId);
    }

    @Override
    public Optional<Order> deleteOrderById(String orderId) {
        Optional<Order> orderToDelete = orderRepo.findById(orderId);
        orderRepo.deleteById(orderId);
        return orderToDelete;
    }

    @Override
    public Optional<Order> updateOrder(Order order) {
        return Optional.of(orderRepo.save(order));
    }

    @Override
    public Optional<Order> saveOrder(OrderTO orderTo) {
        Order order = new Order();
        order.setOrderId(orderTo.getOrderId());
        order.setCustomerId(orderTo.getCustomerId());
        order.setSalerId(orderTo.getSalerId());
        order.setOrderStatus(orderTo.getOrderStatus());
        order.setOrderDate(orderTo.getOrderDate());
        order.setTotalAmount(orderTo.getTotalAmount());
        return Optional.of(orderRepo.save(order));
    }


}
