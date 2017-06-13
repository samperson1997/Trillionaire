package trillionaire.dao;


import trillionaire.model.MeanPrice;

import java.util.List;

/**
 * Created by USER on 2017/5/27.
 */
public interface MeanPriceDao {

    /**
     *
     * @param meanPrice
     */
    public void saveMeanPrice(MeanPrice meanPrice);

    /**
     *
     * @param code
     * @return
     */
    public List<MeanPrice> getMeanPriceListByCode(int code);
}
