package trillionaire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trillionaire.dao.DayRecordDao;
import trillionaire.model.DayRecord;
import trillionaire.service.StockService;
import trillionaire.util.DecimalUtil;
import trillionaire.vo.Earnings;
import trillionaire.vo.PriceTarget;
import trillionaire.vo.RecommendationTrends;
import trillionaire.vo.StockAbility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by michaeltan on 2017/5/6.
 */
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private DayRecordDao dayRecordDao;

    private Map<String, Object> getDailyInfo(String code) {
        Map<String, Object> map = new HashMap<String, Object>();
        int stock = Integer.parseInt(code);
        List<DayRecord> list = dayRecordDao.getDayRecordsByCode(stock);
        List<String> ma5 = calculateMA(list, 5);
        List<String> ma10 = calculateMA(list, 10);
        List<String> ma30 = calculateMA(list, 30);
        map.put("candle", list);
        map.put("ma5", ma5);
        map.put("ma10", ma10);
        map.put("ma30", ma30);
        return map;
    }

    private Map<String, Object> getWeeklyInfo(String code) {
        Map<String, Object> map = new HashMap<String, Object>();

        return map;
    }

    private Map<String, Object> getMonthlyInfo(String code) {
        Map<String, Object> map = new HashMap<String, Object>();

        return map;
    }

    private Map<String, Object> getAnnualInfo(String code) {
        Map<String, Object> map = new HashMap<String, Object>();

        return map;
    }

    public Map<String, Object> getStockInfo(String code, String span) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (span.equals("daily")) {
            map = getDailyInfo(code);
        } else if (span.equals("weekly")) {
            map = getWeeklyInfo(code);
        } else if (span.equals("monthly")) {
            map = getMonthlyInfo(code);
        } else if (span.equals("annual")){
            map = getAnnualInfo(code);
        }else {
            map = null;
        }
        return map;
    }

    public Map<String, Object> getSimilarStock(String input) {

        return null;
    }

    public StockAbility getStockAbility(String code) {


        return null;
    }

    public List<Earnings> getEarnings(String code) {

        return null;
    }

    public double getRecommendationRating(String code) {

        return 0;
    }

    public RecommendationTrends getRecommendationTrends(String code) {

        return null;
    }

    public PriceTarget getPriceTarget(String code) {
        return null;
    }

    public double getOBV(String code) {
        return 0.00;
    }

    public List<Double> getKDJ(String code) {
        return null;
    }

    public List<Double> getBIAS(String code) {
        return null;
    }

    public List<Double> getMACD(String code) {
        return null;
    }

    private double calculateProfitAbility() {
        double result = 0.0;
        double roe;   //净资产收益率
        double netProfitRatio; //净利率
        double grossProfitRate;  //毛利率
        double netProfit;   //净利润(万元)
        double esp;   //每股收益
        double income;   //营业收入(百万元)
        double bips;    //每股主营业务收入(元)

        return result;
    }

    private double calculateOperationAbility() {
        double result = 0.0;
        double arTurnover;  //应收账款周转率(次)
        double arTurnDays;   //应收账款周转天数(天)
        double inventoryTurnover; //存货周转率(次)
        double inventoryDays;   //存货周转天数(天)
        double currentAssetTurnover; //流动资产周转率(次)
        double currentAssetDays;   //流动资产周转天数(天)


        return result;
    }

    private double calculateGrowthAbility() {
        double result = 0.0;
        double mbrg; //主营业务收入增长率(%)
        double nprg; //净利润增长率(%)
        double nav; //净资产增长率
        double targ; //总资产增长率
        double epsg; //每股收益增长率
        double seg; //股东权益增长率

        return result;
    }

    private double calculateDebtPayingAbility() {
        double result = 0.0;
        double currentRatio; //流动比率
        double quickRatio; //速动比率
        double cashRatio; //现金比率
        double icRatio; //利息支付倍数
        double sheqRatio; //股东权益比率
        double adRatio; //股东权益增长率

        return result;
    }

    private List<String> calculateMA(List<DayRecord> list, int dayCount) {
        List<String> result = new ArrayList<String>();
        String s;
        double sum;
        for (int i = 0; i < list.size(); i++) {
            if (i < dayCount) {
                s = "-";
            } else {
                sum = 0.00;
                for (int j = 0; j < dayCount; j++) {
                    sum += list.get(i - j).getAdjClose();
                }
                sum = sum / dayCount;
                s = DecimalUtil.RemainTwoDecimal(sum);
            }
            result.add(s);
        }
        return result;
    }
}