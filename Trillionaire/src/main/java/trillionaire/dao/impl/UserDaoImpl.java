package trillionaire.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trillionaire.dao.UserDao;
import trillionaire.model.Stock;
import trillionaire.model.User;

import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by michaeltan on 2017/5/9.
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User getUser(int userId) {

//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();

        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);

//        tx.commit();
//        session.close();

        return user;
    }

    @Override
    public User getUserByEmail(String email) {

//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from User where email = ?");
        query.setParameter(0,email);
        User user = query.uniqueResult();

//        tx.commit();
//        session.close();

        return user;
    }

    @Override
    public void saveOrUpdateUser(User user) {

//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);

//        tx.commit();
//        session.close();

    }

    @Override
    public void addConcernedStock(int userId, int code) {

//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);
        Stock stock = session.get(Stock.class, code);
        user.getConcernedStocks().add(stock);
        session.save(user);

//        tx.commit();
//        session.close();

    }

    @Override
    public void deleteConcernedStock(int userId, int code) {

//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, userId);
        Stock stock = session.get(Stock.class, code);
        user.getConcernedStocks().remove(stock);
        session.save(user);

//        tx.commit();
//        session.close();

    }

    @Override
    public Set<Stock> getUserStocks(int userId) {
//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();

        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, userId);
        Set<Stock> set = user.getConcernedStocks();
        set.size();

//        tx.commit();
//        session.close();

        return set;
    }


}
