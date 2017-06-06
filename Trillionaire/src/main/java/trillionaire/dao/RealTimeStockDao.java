package trillionaire.dao;

import trillionaire.model.RealTimeStock;

import java.util.List;

/**
 * Created by USER on 2017/5/17.
 */
public interface RealTimeStockDao {

    public RealTimeStock getRealTimeByCode(int code);

    public List<RealTimeStock> getAll();

}
