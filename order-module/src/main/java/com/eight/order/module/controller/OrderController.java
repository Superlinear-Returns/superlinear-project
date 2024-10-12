package com.eight.order.module.controller;

import com.eight.order.module.model.DataObject;
import com.eight.order.module.model.EmptyDto;
import com.eight.order.module.model.OrderTO;
import com.eight.order.module.service.OrderService;
import com.eight.order.module.model.Order;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
    public ResponseEntity<Order> getOrderById(@RequestParam("orderId") int orderId) {
        Cache<String, DataObject> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(10)
                .build();

        String key = "A";
        DataObject dataObject = cache.getIfPresent(key);
        if (dataObject == null) {
            System.out.println("null");
        }


        DataObject dataObject1 = new DataObject("Data for A");
        cache.put("A", dataObject1);
        if (dataObject == null) {
            System.out.println("null");
        }

        dataObject = cache
                .get(key, k -> DataObject.get("Data for A"));

        if (dataObject == null) {
            System.out.println("null");
        }
        if (Objects.equals("Data for A", dataObject.getData())) {
            System.out.println("equal");
        }


        return ResponseEntity.ok(orderService.getOrderById(orderId).orElse(null));
    }

    @GetMapping("/delete")
    @Operation(summary = "刪除訂單", description = "訂單編號刪除訂單")
    public ResponseEntity<Order> deleteOrderById(@RequestParam("orderId") int orderId) {
        return ResponseEntity.ok(orderService.deleteOrderById(orderId).orElse(new EmptyDto()));
    }

    @PostMapping("/save")
    @Operation(summary = "建立訂單", description = "訂單物件建立訂單")
    public ResponseEntity<Order> saveOrderById(@Valid @RequestBody OrderTO order) {
        return ResponseEntity.ok(orderService.saveOrder(order).orElse(null));
    }

    @PostMapping("/update")
    @Operation(summary = "更新訂單", description = "訂單物件更新訂單")
    public ResponseEntity<Order> updateOrderById(@Valid @RequestBody Order order) {
        return ResponseEntity.ok(orderService.updateOrder(order).orElse(null));
    }
}
