package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.User;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
    private SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User add(User user) {
        logger.debug("Method add() invoked");
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert User entity with email "
                    + user.getEmail(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        logger.debug("Method findByEmail() invoked");
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User "
                    + "WHERE email = :mail", User.class);
            query.setParameter("mail", email);
            return query.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to find user with email "
                    + email, e);
        }
    }
}
