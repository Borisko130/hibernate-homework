package com.dev.cinema.controller;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.dto.order.OrderMapper;
import com.dev.cinema.model.dto.order.OrderRequestDto;
import com.dev.cinema.model.dto.order.OrderResponseDto;
import com.dev.cinema.service.OrderService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestBody OrderRequestDto dto) {
        Order order = orderMapper.convertDtoToOrder(dto);
        orderService.completeOrder(order.getTickets(), order.getUser());
    }

    @GetMapping("/userId")
    public List<OrderResponseDto> getOrders(@RequestParam Long userId) {
        return orderService.getOrderHistory(userId).stream()
                .map(orderMapper::convertOrderToDto)
                .collect(Collectors.toList());
    }
}
