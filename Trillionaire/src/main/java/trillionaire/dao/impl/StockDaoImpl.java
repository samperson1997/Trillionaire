package trillionaire.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import org.springframework.stereotype.Repository;
import trillionaire.model.Area;
import trillionaire.model.Concept;
import trillionaire.model.Industry;
import trillionaire.model.Stock;
import trillionaire.dao.StockDao;

import javax.annotation.Resource;

@Repository
public class StockDaoImpl implements StockDao{

    @Resource
    SessionFactory sessionFactory ;

    public List<Stock> getAllStocks() {
        // TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Query<Stock> query = session.createQuery("from Stock");

        List<Stock> result = query.list();

        tx.commit();
        session.close();
        return result;
    }

    public Set<Stock> getStocksByIndustry(String industryName) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Query<Industry> query = session.createQuery("from Industry where name = ?");
        query.setParameter(0, industryName);

        Industry industry = query.uniqueResult();

        Set<Stock> result = industry.getStocks();
        result.size();

        tx.commit();
        session.close();

        return result;
    }

    public Set<Stock> getStocksByArea(String areaName) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Query<Area> query = session.createQuery("from Area where name = ?");
        query.setParameter(0, areaName);

        Area area = query.uniqueResult();

        if(area==null){
            return null;
        }

        Set<Stock> result = area.getStocks();
        result.size();

        tx.commit();
        session.close();

        return result;
    }

    public Set<Stock> getStocksByConcept(String conceptName) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Query<Concept> query = session.createQuery("from Concept where name = ?");
        query.setParameter(0, conceptName);

        Concept concept = query.uniqueResult();

        Set<Stock> result = concept.getStocks();
        result.size();

        tx.commit();
        session.close();

        return result;
    }

}
