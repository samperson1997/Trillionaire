package trillionaire.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import trillionaire.dao.MeanPriceDao;
import trillionaire.model.MeanPrice;

import java.util.List;

/**
 * Created by USER on 2017/5/27.
 */
@Repository
public class MeanPriceDaoImpl implements MeanPriceDao{

    @Autowired
    SessionFactory sessionFactory;


    public void saveMeanPrice(MeanPrice meanPrice) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(meanPrice);

        tx.commit();
        session.close();

    }

    public List<MeanPrice> getMeanPriceListByCode(int code) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Query<MeanPrice> query = session.createNativeQuery("select * from mean_price where code = "+code+" order by date asc", MeanPrice.class);
        List<MeanPrice> result = query.list();

        tx.commit();
        session.close();

        return result;

    }


}
