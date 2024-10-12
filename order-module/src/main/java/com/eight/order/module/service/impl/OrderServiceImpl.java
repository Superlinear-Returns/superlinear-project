package com.eight.order.module.service.impl;

import com.eight.order.module.model.Order;
import com.eight.order.module.model.OrderStatusEnum;
import com.eight.order.module.model.OrderTO;
import com.eight.order.module.repository.IOrderRepo;
import com.eight.order.module.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final IOrderRepo orderRepo;

    public OrderServiceImpl(IOrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public Optional<Order> getOrderById(int orderId) {
//        Optional<Order> orderOp = orderRepo.findById(orderId);
//        if(orderOp.isPresent()){
//        }
        return orderRepo.findById(orderId);
    }

    @Override
    public Optional<Order> deleteOrderById(int orderId) {
        Optional<Order> orderToDelete = orderRepo.findById(orderId);
        orderRepo.deleteById(orderId);
        return orderToDelete;
    }

    @Override
    public Optional<Order> updateOrder(Order order) {
        try {
            orderRepo.save(order);
        } catch (RestClientException re) {
            log.error("Update Error: Order " + re);
        }
        return Optional.of(order);
    }

    @Override
    public Optional<Order> saveOrder(OrderTO orderTo) {
        Order order = new Order();
        order.setOrderId(orderTo.getOrderId());
        order.setCustomerId(orderTo.getCustomerId());
        order.setSalerId(orderTo.getSalerId());
        order.setOrderStatus(orderTo.getOrderStatus().code);
        order.setOrderDate(orderTo.getOrderDate());
        order.setTotalAmount(orderTo.getTotalAmount());

        try {
            orderRepo.save(order);
        } catch (RestClientException re) {
            log.error("Save Error: Order " + re);
        }
        return Optional.of(order);
    }


}
