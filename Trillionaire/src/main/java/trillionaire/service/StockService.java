package trillionaire.service;


import trillionaire.model.RealTimeStock;
import trillionaire.vo.*;

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
     * 获取股票实时数据
     *
     * @param code 股票代码
     * @return 不同指标的map映射
     */
    public RealTimeStock updateRealTime(String code);

    /**
     * 股票联想
     *
     * @param input 输入
     * @return 不同股票的map映射
     */
    public List<AssociateStock> associate(String input);

    /**
     * 股票关联分析
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
     * 获得股票信息
     *
     * @param code 股票代码
     * @return 股票信息
     */
    public StockSquare getSquare(String code);

    /**
     * 获得KDJ
     *
     * @param code 股票代码
     * @return KDJ
     */
    public List<KDJ> getKDJ(String code);

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
    
    /**
     * 获得涨跌幅
     *
     * @param code1 股票1代码
     * @param code2 股票2代码
     * @param code3 股票3代码
     * @return margin
     */
    public Map<String, Object> getMargin(String code1, String code2, String code3);


}
