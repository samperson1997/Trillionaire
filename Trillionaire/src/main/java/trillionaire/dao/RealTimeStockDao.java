package trillionaire.dao;

import trillionaire.model.RealTimeStock;

import java.util.List;

/**
 * Created by USER on 2017/5/17.
 */
public interface RealTimeStockDao {

    /**
     *
     * @param code
     * @return
     */
    public RealTimeStock getRealTimeByCode(int code);

    /**
     *
     * @return
     */
    public List<RealTimeStock> getAll();

}
