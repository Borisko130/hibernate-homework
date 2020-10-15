package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.ShoppingCartDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private static final Logger logger = Logger.getLogger(ShoppingCartDaoImpl.class);

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        logger.debug("Method add() invoked");
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert ShoppingCart entity for User with id "
                    + shoppingCart.getUser().getId(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        logger.debug("Method getByUser() invoked");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<ShoppingCart> query = session.createQuery("FROM ShoppingCart sc "
                    + "JOIN FETCH sc.user "
                    + "LEFT JOIN FETCH sc.tickets t "
                    + "LEFT JOIN FETCH t.movieSession "
                    + "WHERE sc.user.id = :userId", ShoppingCart.class);
            query.setParameter("userId", user.getId());
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to find ShoppingCart for user with id "
                    + user.getId(), e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        logger.debug("Method update() invoked");
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Failed to update ShoppingCart with id "
                    + shoppingCart.getId(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
