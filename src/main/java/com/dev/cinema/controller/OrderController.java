package com.dev.cinema.controller;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.dto.order.OrderMapper;
import com.dev.cinema.model.dto.order.OrderRequestDto;
import com.dev.cinema.model.dto.order.OrderResponseDto;
import com.dev.cinema.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private OrderMapper orderMapper;

    @PostMapping("/complete")
    public void completeOrder(@RequestBody OrderRequestDto dto) {
        Order order = orderMapper.convertDtoToOrder(dto);
        orderService.completeOrder(order.getTickets(), order.getUser());
    }

    @GetMapping("/userId")
    public List<OrderResponseDto> getOrders() {
return orderService.getOrderHistory()
    }
}
