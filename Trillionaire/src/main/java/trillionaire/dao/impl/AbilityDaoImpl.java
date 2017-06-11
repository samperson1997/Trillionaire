package trillionaire.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import trillionaire.dao.AbilityDao;
import trillionaire.model.DebtPayingAbility;
import trillionaire.model.DevelopingAbility;
import trillionaire.model.OperationAbility;
import trillionaire.model.Profitability;

import javax.annotation.Resource;
import javax.ejb.Local;
import java.sql.Date;
import java.time.LocalDate;

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

    @Override
    public Profitability getProfitability(int code) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        int year = LocalDate.now().getYear();
        int quarter = (LocalDate.now().getMonthValue()-1)/4;
        if(quarter==0){
            quarter=4;
            year = year-1;
        }
        Query<Profitability> query = session.createQuery("from Profitability where code = ? and year = ? and quarter = ?");
        query.setParameter(0,code);
        query.setParameter(1,year);
        query.setParameter(2,quarter);
        Profitability result = query.uniqueResult();

        tx.commit();
        session.close();

        return result;
    }

    @Override
    public OperationAbility getOperationAbility(int code) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        int year = LocalDate.now().getYear();
        int quarter = (LocalDate.now().getMonthValue()-1)/4;
        if(quarter==0){
            quarter=4;
            year = year-1;
        }
        Query<OperationAbility> query = session.createQuery("from OperationAbility where code = ? and year = ? and quarter = ?");
        query.setParameter(0,code);
        query.setParameter(1,year);
        query.setParameter(2,quarter);
        OperationAbility result = query.uniqueResult();

        tx.commit();
        session.close();

        return result;
    }

    @Override
    public DevelopingAbility DevelopingAbility(int code) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        int year = LocalDate.now().getYear();
        int quarter = (LocalDate.now().getMonthValue()-1)/4;
        if(quarter==0){
            quarter=4;
            year = year-1;
        }
        Query<DevelopingAbility> query = session.createQuery("from DevelopingAbility where code = ? and year = ? and quarter = ?");
        query.setParameter(0,code);
        query.setParameter(1,year);
        query.setParameter(2,quarter);
        DevelopingAbility result = query.uniqueResult();

        tx.commit();
        session.close();

        return result;
    }

    @Override
    public DebtPayingAbility getDebtPayingAbility(int code) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        int year = LocalDate.now().getYear();
        int quarter = (LocalDate.now().getMonthValue()-1)/4;
        if(quarter==0){
            quarter=4;
            year = year-1;
        }
        Query<DebtPayingAbility> query = session.createQuery("from DebtPayingAbility where code = ? and year = ? and quarter = ?");
        query.setParameter(0,code);
        query.setParameter(1,year);
        query.setParameter(2,quarter);
        DebtPayingAbility result = query.uniqueResult();

        tx.commit();
        session.close();

        return result;
    }
}
