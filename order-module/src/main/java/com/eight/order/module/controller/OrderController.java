package com.eight.order.module.controller;

import com.eight.order.module.model.OrderTO;
import com.eight.order.module.service.OrderService;
import com.eight.order.module.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RestController
@Validated
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/query")
    @Operation(summary = "查詢訂單", description = "訂單編號取得訂單")
    public ResponseEntity<Order> getOrderById(@RequestParam("orderId") String orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId).orElse(null));
    }

    @GetMapping("/delete")
    @Operation(summary = "刪除訂單", description = "訂單編號刪除訂單")
    public ResponseEntity<Order> deleteOrderById(@RequestParam("orderId") String orderId) {
        return ResponseEntity.ok(orderService.deleteOrderById(orderId).orElse(null));
    }

    @PostMapping("/save")
    @Operation(summary = "建立訂單", description = "訂單物件建立訂單")
    public ResponseEntity<Order> saveOrderById(@Valid @RequestBody OrderTO order) {
        System.out.println("HI");
        return ResponseEntity.ok(orderService.saveOrder(order).orElse(null));
    }

    @PostMapping("/update")
    @Operation(summary = "更新訂單", description = "訂單物件更新訂單")
    public ResponseEntity<Order> updateOrderById(@Valid @RequestBody Order order) {
        return ResponseEntity.ok(orderService.updateOrder(order).orElse(null));
    }
}
