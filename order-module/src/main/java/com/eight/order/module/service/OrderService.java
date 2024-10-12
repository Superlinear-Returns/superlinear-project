package com.eight.order.module.service;

import com.eight.order.module.model.Order;
import com.eight.order.module.model.OrderTO;

import java.util.Optional;

public interface OrderService {

    Optional<Order> getOrderById(int orderId);

    Optional<Order> deleteOrderById(int orderId);

    Optional<Order> saveOrder(OrderTO order);

    Optional<Order> updateOrder(Order order);
}
