package com.dev.cinema.service.impl;

import com.dev.cinema.dao.ShoppingCartDao;
import com.dev.cinema.dao.TicketDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;
    @Inject
    private TicketDao ticketDao;

    @Override
    public void addSession(MovieSession movieSession, User user) {

    }

    @Override
    public ShoppingCart getByUser(User user) {
        return null;
    }

    @Override
    public void registerNewShoppingCart(User user) {

    }
}
