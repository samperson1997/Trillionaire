package trillionaire.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trillionaire.dao.DayRecordDao;
import trillionaire.dao.RealTimeStockDao;
import trillionaire.model.DayRecord;
import trillionaire.model.RealTimeStock;
import trillionaire.model.Stock;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by USER on 2017/5/17.
 */
@Repository
@Transactional
public class RealTimeStockDaoImpl implements RealTimeStockDao{

    private Map<Integer, RealTimeStock> stockMap;
    private Timer timer;
    private UpdateThread updateThread;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    DayRecordDao dayRecordDao;

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
//        timer.schedule(updateThread,0,1000*60);


    }

    public RealTimeStock getRealTimeByCode(int code) {
        if(stockMap!=null){
            if(stockMap.get(code)!=null){
                return stockMap.get(code).clone();
            }
            else{
                Session session = sessionFactory.openSession();
                Transaction tx = session.beginTransaction();

                Stock stock = session.get(Stock.class, code);
                String codeString = String.valueOf(stock.getCode());
                while(codeString.length()<6){
                    codeString = "0" + codeString;
                }
                List<DayRecord> list = dayRecordDao.getDayRecordsByCode(code);
                DayRecord dayRecord = list.get(list.size()-1);

                return new RealTimeStock(codeString, stock.getName(), dayRecord.getChange(), dayRecord.getClose(), dayRecord.getOpen(), dayRecord.getHigh(), dayRecord.getLow(), dayRecord.getClose(), dayRecord.getVolume(), 0, (long)(dayRecord.getDealSum()), 0, 0, 0, 0);
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
