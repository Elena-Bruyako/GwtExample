package com.bruyako.server.repository;

import com.bruyako.server.util.HibernateUtil;
import com.bruyako.shared.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * Created by brunyatko on 24.03.16.
 */
public class UserRepository {

    private SessionFactory sessionFactory;

    public User getUserByLogin(String login) {

        sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(User.class);
        User user = (User) criteria.add(Restrictions.eq("login", login))
                .uniqueResult();

        session.close();
        HibernateUtil.shutdown();

        return user;
    }
}
