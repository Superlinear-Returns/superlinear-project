package com.eight.order.module.controller;

import com.eight.order.module.service.OrderService;
import com.eight.order.module.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Operation(summary = "Get an order by ID", description = "Returns an order based on the provided ID")
    public ResponseEntity<Order> getUserById(@RequestParam("orderId") Integer orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId).orElse(null));
    }
}
