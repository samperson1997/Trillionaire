package trillionaire.service;


import trillionaire.vo.Earnings;
import trillionaire.vo.PriceTarget;
import trillionaire.vo.RecommendationTrends;
import trillionaire.vo.StockAbility;

import java.util.List;
import java.util.Map;

/**
 * Created by michaeltan on 2017/5/6.
 */
public interface StockService {
    /**
     * 获得个股K线
     *
     * @param code 股票代码
     * @param span 时间跨度
     * @return 不同指标的map映射
     */
    public Map<String, Object> getStockInfo(String code, String span);

    /**
     * 股票联想
     *
     * @param input 输入
     * @return 不同股票的map映射
     */
    public Map<String, Object> associate(String input);

    /**
     * 股票联想
     *
     * @param code 输入
     * @return 不同股票的map映射
     */
    public Map<String, Object> getSimilarStock(String code);

    /**
     * 获得股票能力雷达图数据
     *
     * @param code 股票代码
     * @return 股票能力的VO
     */
    public StockAbility getStockAbility(String code);

    /**
     * 获得收益预测
     *
     * @param code 股票代码
     * @return 股票Earnings的list
     */
    public List<Earnings> getEarnings(String code);

    /**
     * 获得股票推荐等级
     *
     * @param code 股票代码
     * @return 股票推荐等级
     */
    public double getRecommendationRating(String code);

    /**
     * 获得股票推荐操作趋势
     *
     * @param code 股票代码
     * @return RecommendationTrends的VO
     */
    public RecommendationTrends getRecommendationTrends(String code);

    /**
     * 获得股票价格的目标
     *
     * @param code 股票代码
     * @return 股票价格目标
     */
    public PriceTarget getPriceTarget(String code);

    /**
     * 获得股票热度
     *
     * @param code 股票代码
     * @return 股票热度
     */
    public double getVR(String code);

    /**
     * 获得KDJ
     *
     * @param code 股票代码
     * @return KDJ
     */
    public Map<String, Object> getKDJ(String code);

    /**
     * 获得BIAS
     *
     * @param code 股票代码
     * @return BIAS
     */
    public Map<String, Object> getBIAS(String code);

    /**
     * 获得MACD
     *
     * @param code 股票代码
     * @return MACD
     */
    public Map<String, Object> getMACD(String code);


}
