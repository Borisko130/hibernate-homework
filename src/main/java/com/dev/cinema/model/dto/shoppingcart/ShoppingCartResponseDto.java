package com.dev.cinema.model.dto.shoppingcart;

import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import java.util.List;

public class ShoppingCartResponseDto {
    private Long id;
    private List<Ticket> tickets;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
