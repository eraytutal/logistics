package com.vbt.logistics.controller;

// controller/OrderController.java

import com.vbt.logistics.dto.*;
import com.vbt.logistics.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto create(@Valid @RequestBody CreateOrderRequestDto req) {
        return service.createOrder(req);
    }

    @GetMapping("/{id}")
    public OrderDto get(@PathVariable Long id) {
        return service.getOrder(id);
    }

    @PostMapping("/{orderId}/items")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderItemDto addItem(@PathVariable Long orderId, @Valid @RequestBody AddOrderItemRequestDto req) {
        return service.addItem(orderId, req);
    }

    @GetMapping("/{orderId}/items")
    public List<OrderItemDto> items(@PathVariable Long orderId) {
        return service.listItems(orderId);
    }

    @PostMapping("/{orderId}/stops")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderStopDto addStop(@PathVariable Long orderId, @Valid @RequestBody AddOrderStopRequestDto req) {
        return service.addStop(orderId, req);
    }

    @GetMapping("/{orderId}/stops")
    public List<OrderStopDto> stops(@PathVariable Long orderId) {
        return service.listStops(orderId);
    }
}

