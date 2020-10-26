package com.dev.cinema.model.dto.order;

import com.dev.cinema.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order convertDtoToOrder(OrderRequestDto dto) {
        Order order = new Order();
        order.setTitle(dto.getTitle());
        order.setDescription(dto.getDescription());
        return order;
    }

    public OrderResponseDto convertOrderToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setTitle(order.getTitle());
        dto.setDescription(order.getDescription());
        return dto;
    }
}
