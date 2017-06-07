package trillionaire.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import trillionaire.dao.RealTimeStockDao;
import trillionaire.model.RealTimeStock;
import trillionaire.model.Stock;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by USER on 2017/5/17.
 */
@Repository
public class RealTimeStockDaoImpl implements RealTimeStockDao{

    private Map<Integer, RealTimeStock> stockMap;
    private Timer timer;
    private UpdateThread updateThread;

    @Autowired
    SessionFactory sessionFactory;

    public RealTimeStockDaoImpl(){

        stockMap = new ConcurrentHashMap<>();
//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//        Query query = session.createNativeQuery("select code from stock where");
//        List<Integer> codeList = query.list();
//        tx.commit();
//        session.close();

        updateThread = new UpdateThread(new RealTimeUpdater());
        timer = new Timer(true);
        timer.schedule(updateThread,0,1000*60);


    }

    public RealTimeStock getRealTimeByCode(int code) {
        if(stockMap!=null){
            if(stockMap.get(code)!=null){
                return stockMap.get(code).clone();
            }
        }

        return null;
    }

    public List<RealTimeStock> getAll(){

        if(stockMap==null){
            return null;
        }

        ArrayList<RealTimeStock> result = new ArrayList<RealTimeStock>();
        for(Map.Entry<Integer, RealTimeStock> entry: stockMap.entrySet()){
            result.add(entry.getValue().clone());
        }

        return result;

    }

    public class UpdateThread extends TimerTask {

        private RealTimeUpdater updater;

        public UpdateThread(RealTimeUpdater updater){
            this.updater = updater;

        }

        @Override
        public void run() {
            updater.updateNewInfo(stockMap);
        }
    }
}
