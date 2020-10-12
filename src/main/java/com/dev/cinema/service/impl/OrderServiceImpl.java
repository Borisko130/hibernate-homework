package com.dev.cinema.service.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    OrderDao orderDao;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setTickets(tickets);
        order.setUser(user);
        orderDao.add(order);
        return order;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrdersByUser(user);
    }
}
