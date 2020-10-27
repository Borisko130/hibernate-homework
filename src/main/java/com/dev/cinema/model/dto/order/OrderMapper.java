package com.dev.cinema.model.dto.order;

import com.dev.cinema.model.Order;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponseDto convertOrderToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setTickets(order.getTickets());
        dto.setOrderDate(order.getOrderDate());
        dto.setUser(order.getUser());
        return dto;
    }
}
