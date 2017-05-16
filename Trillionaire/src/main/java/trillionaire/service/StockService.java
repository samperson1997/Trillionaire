package trillionaire.service;


import trillionaire.model.Earnings;
import trillionaire.model.RecommendationTrends;
import trillionaire.model.StockAbility;

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

    /**
     * 股票联想
     *
     * @param input 输入
     * @return 不同股票的map映射
     */
    public Map<String, Object> getSimilarStock(String input);

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
     * @return 股票Earnings的VO
     */
    public Map<String, Earnings> getEarnings(String code);

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
    public double getPriceTarget(String code);



}
