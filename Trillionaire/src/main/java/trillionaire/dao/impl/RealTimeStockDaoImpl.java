package trillionaire.dao.impl;

import trillionaire.dao.RealTimeStockDao;
import trillionaire.model.RealTimeStock;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by USER on 2017/5/17.
 */
public class RealTimeStockDaoImpl implements RealTimeStockDao{

    private Map<Integer, RealTimeStock> stockMap;
    private Timer timer;
    private UpdateThread updateThread;

    public RealTimeStockDaoImpl(){

        updateThread = new UpdateThread(new RealTimeUpdater());
        timer = new Timer(true);
        timer.schedule(updateThread,0,1000*60);


    }

    public RealTimeStock getRealTimeByCode(int code) {

        if(stockMap!=null){
            return stockMap.get(code);
        }

        return null;
    }

    public class UpdateThread extends TimerTask {

        private RealTimeUpdater updater;

        public UpdateThread(RealTimeUpdater updater){
            this.updater = updater;

        }

        @Override
        public void run() {
            stockMap = updater.getNewInfo();
        }
    }
}
