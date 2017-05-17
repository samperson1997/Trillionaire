package trillionaire.dao;

import trillionaire.model.RealTimeStock;

/**
 * Created by USER on 2017/5/17.
 */
public interface RealTimeStockDao {

    public RealTimeStock getRealTimeByCode(int code);

}
