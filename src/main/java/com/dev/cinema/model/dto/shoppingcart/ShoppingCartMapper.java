package com.dev.cinema.model.dto.shoppingcart;

import com.dev.cinema.model.ShoppingCart;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ShoppingCartMapper {
    public ShoppingCart convertDtoToShoppingCart(ShoppingCartRequestDto dto) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setTickets(new ArrayList<>(dto.getTickets()));
        shoppingCart.setUser(dto.getUser());
        return shoppingCart;
    }

    public ShoppingCartResponseDto convertShoppingCartToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setId(shoppingCart.getId());
        dto.setTickets(shoppingCart.getTickets());
        dto.setUser(shoppingCart.getUser());
        return dto;
    }
}
