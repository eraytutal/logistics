package com.vbt.logistics.controller;


import com.vbt.logistics.dto.*;
import com.vbt.logistics.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public PageResponseDto<OrderItemDto> items(
            @PathVariable Long orderId,
            @PageableDefault(size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            }) Pageable pageable
    ) {
        return service.listItems(orderId, pageable);
    }

    @PostMapping("/{orderId}/stops")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderStopDto addStop(@PathVariable Long orderId, @Valid @RequestBody AddOrderStopRequestDto req) {
        return service.addStop(orderId, req);
    }

    @GetMapping("/{orderId}/stops")
    public PageResponseDto<OrderStopDto> stops(
            @PathVariable Long orderId,
            @PageableDefault(size = 20)
            @SortDefault(sort = "seq", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return service.listStops(orderId, pageable);
    }

    @GetMapping("/{orderId}/parties")
    public PageResponseDto<OrderPartyDto> parties(
            @PathVariable Long orderId,
            @SortDefault(sort = "role", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.listParties(orderId, pageable);
    }
}
