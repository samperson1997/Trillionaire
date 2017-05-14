package trillionaire.service;


import java.util.Map;

/**
 * Created by michaeltan on 2017/5/6.
 */
public interface StockService {
    /**
     * 获得个股信息
     *
     * @param code 股票代码
     * @return 不同指标的map映射
     */
    public Map<String, Object> getStockInfo(String code);



}
