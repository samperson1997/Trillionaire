package trillionaire.dao;


import trillionaire.model.MeanPrice;

import java.util.List;

/**
 * Created by USER on 2017/5/27.
 */
public interface MeanPriceDao {

    public void saveMeanPrice(MeanPrice meanPrice);

    public List<MeanPrice> getMeanPriceListByCode(int code);
}
