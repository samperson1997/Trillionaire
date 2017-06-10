package trillionaire.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import trillionaire.dao.UserDao;
import trillionaire.model.User;

import java.sql.Date;

/**
 * Created by michaeltan on 2017/5/9.
 */
@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User getUser(int userId) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();


        User user = session.get(User.class, userId);

        tx.commit();
        session.close();

        return user;
    }

    @Override
    public User getUserByEmail(String email) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Query<User> query = session.createQuery("from User where email = ?");
        query.setParameter(0,email);
        User user = query.uniqueResult();

        tx.commit();
        session.close();

        return user;
    }

    @Override
    public void saveOrUpdateUser(User user) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(user);

        tx.commit();
        session.close();

    }


}
