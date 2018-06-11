package trillionaire.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trillionaire.dao.StrategyDao;
import trillionaire.model.Strategy;
import trillionaire.model.User;

import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by USER on 2017/6/8.
 */

@Repository
@Transactional
public class StrategyDaoImpl implements StrategyDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public int saveStrategy(Strategy strategy) {

//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(strategy);

//        tx.commit();
//        session.close();

        return strategy.getSid();
    }

    @Override
    public void deletStrategy(int sid) {

//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
        Session session = sessionFactory.getCurrentSession();
        Object o = session.get(Strategy.class, sid);
        session.delete(o);

//        tx.commit();
//        session.close();

    }

    @Override
    public Set<Strategy> getUserStrategy(int userId) {

//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, userId);
        Set<Strategy> result = user.getStrategies();
        result.size();

//        tx.commit();
//        session.close();

        return result;
    }

    @Override
    public Strategy getStrategy(int sid) {

//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
        Session session = sessionFactory.getCurrentSession();
        Strategy strategy = session.get(Strategy.class, sid);


//        tx.commit();
//        session.close();

        return strategy;
    }
}
