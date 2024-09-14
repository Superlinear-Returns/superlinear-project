package com.eight.order.module.service;

import com.eight.order.module.model.Order;

import java.util.Optional;

public interface OrderService {

    Optional<Order> getOrderById(Integer orderId);
}
