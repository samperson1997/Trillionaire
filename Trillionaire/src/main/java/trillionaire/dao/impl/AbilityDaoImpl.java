package trillionaire.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import trillionaire.dao.AbilityDao;
import trillionaire.model.DebtPayingAbility;
import trillionaire.model.DevelopingAbility;
import trillionaire.model.OperationAbility;
import trillionaire.model.Profitability;

import javax.annotation.Resource;
import java.sql.Date;

/**
 * Created by USER on 2017/5/27.
 */
@Repository
public class AbilityDaoImpl implements AbilityDao {

    @Autowired
    SessionFactory sessionFactory;

    public void saveProfitability(Profitability profitability) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(profitability);

        tx.commit();
        session.close();
    }

    public void saveOperationAbility(OperationAbility operationAbility) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(operationAbility);

        tx.commit();
        session.close();
    }

    public void saveDevelopingAbility(DevelopingAbility developingAbility) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(developingAbility);

        tx.commit();
        session.close();
    }

    public void saveDebtPayingAbility(DebtPayingAbility debtPayingAbility) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(debtPayingAbility);

        tx.commit();
        session.close();
    }
}
