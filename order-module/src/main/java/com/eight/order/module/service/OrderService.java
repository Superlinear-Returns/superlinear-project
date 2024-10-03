package com.eight.order.module.service;

import com.eight.order.module.model.Order;
import com.eight.order.module.model.OrderTO;

import java.util.Optional;

public interface OrderService {

    Optional<Order> getOrderById(String orderId);

    Optional<Order> deleteOrderById(String orderId);

    Optional<Order> saveOrder(OrderTO order);

    Optional<Order> updateOrder(Order order);
}
