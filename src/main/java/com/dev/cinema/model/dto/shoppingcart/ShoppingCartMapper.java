package com.dev.cinema.model.dto.shoppingcart;

import com.dev.cinema.model.ShoppingCart;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {

    public ShoppingCartResponseDto convertShoppingCartToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setId(shoppingCart.getId());
        dto.setTickets(shoppingCart.getTickets());
        dto.setUser(shoppingCart.getUser());
        return dto;
    }
}
