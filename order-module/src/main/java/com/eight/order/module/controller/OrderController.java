package com.eight.order.module.controller;

import com.eight.order.module.service.OrderService;
import com.eight.order.module.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/order")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @Operation(summary = "訂單編號拿訂單", description = "ddddd")
    public ResponseEntity<Order> getOrderById(@RequestParam("orderId") Integer orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId).orElse(null));
    }
}
