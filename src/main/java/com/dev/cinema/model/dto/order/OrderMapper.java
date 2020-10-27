package com.dev.cinema.model.dto.order;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public OrderResponseDto convertOrderToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setTickets(order.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        dto.setOrderDate(order.getOrderDate());
        dto.setUserId(order.getUser().getId());
        return dto;
    }
}
