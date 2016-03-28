package com.bruyako.server.repository;

import com.bruyako.server.util.HibernateUtil;
import com.bruyako.shared.User;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by brunyatko on 24.03.16.
 */
public class UserRepository extends BaseRepository <User> {

    @Override
    public User getById(int id) {

        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        User user = (User) session.createQuery("select u.id from User u where u.id=:id")
                .setParameter("id", id);

        session.close();
        HibernateUtil.shutdown();

        return user;
    }

    @Override
    public List<User> getAll() {

        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        List<User> users = session.createQuery("from User u")
                .list();

        session.close();
        HibernateUtil.shutdown();

        return users;
    }

}
