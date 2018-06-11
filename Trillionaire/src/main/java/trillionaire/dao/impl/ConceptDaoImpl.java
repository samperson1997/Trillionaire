package trillionaire.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trillionaire.dao.ConceptDao;
import trillionaire.model.Area;
import trillionaire.model.Concept;
import trillionaire.model.Industry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by USER on 2017/6/12.
 */
@Repository
@Transactional
public class ConceptDaoImpl implements ConceptDao{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Map<Integer, String> getAllConcepts() {
        Session session = sessionFactory.getCurrentSession();
//        Transaction tx = session.beginTransaction();

        Map<Integer, String> result = new HashMap<>();

        Query<Concept> query = session.createQuery("from Concept");
        List<Concept> list = query.getResultList();
        for(Concept c: list){
            result.put(c.getCid(), c.getName());
        }

//        tx.commit();
        //session.close();

        return result;
    }

    @Override
    public Map<Integer, String> getAllIndustry() {
        Session session = sessionFactory.getCurrentSession();
//        Transaction tx = session.beginTransaction();

        Map<Integer, String> result = new HashMap<>();

        Query<Industry> query = session.createQuery("from Industry");
        List<Industry> list = query.getResultList();
        for(Industry i: list){
            result.put(i.getIid(), i.getName());
        }

//        tx.commit();
        //session.close();

        return result;
    }

    @Override
    public Map<Integer, String> getAllArea() {
        Session session = sessionFactory.getCurrentSession();
//        Transaction tx = session.beginTransaction();

        Map<Integer, String> result = new HashMap<>();

        Query<Area> query = session.createQuery("from Area");
        List<Area> list = query.getResultList();
        for(Area a: list){
            result.put(a.getAid(), a.getName());
        }

//        tx.commit();
        //session.close();

        return result;
    }
}
