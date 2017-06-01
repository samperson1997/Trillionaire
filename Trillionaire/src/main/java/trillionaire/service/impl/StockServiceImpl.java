package trillionaire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trillionaire.dao.DayRecordDao;
import trillionaire.model.DayRecord;
import trillionaire.service.StockService;
import trillionaire.service.impl.boxjenkins.TimeSeriesPredict;
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

    private TimeSeriesPredict timeSeriesPredict = new TimeSeriesPredict();

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

    @Override
    public Map<String, Object> getStockInfo(String code, String span) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (span.equals("daily")) {
            map = getDailyInfo(code);
        } else if (span.equals("weekly")) {
            map = getWeeklyInfo(code);
        } else if (span.equals("monthly")) {
            map = getMonthlyInfo(code);
        } else {
            map = null;
        }
        return map;
    }

    @Override
    public Map<String, Object> associate(String input) {

        return null;
    }

    @Override
    public Map<String, Object> getSimilarStock(String code) {

        return null;
    }

    @Override
    public StockAbility getStockAbility(String code) {


        return null;
    }

    @Override
    public List<Earnings> getEarnings(String code) {

        return null;
    }

    @Override
    public double getRecommendationRating(String code) {

        return 0;
    }

    @Override
    public RecommendationTrends getRecommendationTrends(String code) {

        return null;
    }

    @Override
    public PriceTarget getPriceTarget(String code) {
        List<DayRecord> list = dayRecordDao.getDayRecordsByCode(1);
        double[] highArray = new double[list.size()];
        double[] closeArray = new double[list.size()];
        double[] lowArray = new double[list.size()];
        double[] averageArray = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            highArray[i] = list.get(i).getHigh();
            closeArray[i] = list.get(i).getAdjClose();
            lowArray[i] = list.get(i).getLow();
            averageArray[i] = list.get(i).getDealSum() / list.get(i).getVolume();
        }
        double high = timeSeriesPredict.predict(highArray);
        double close = timeSeriesPredict.predict(closeArray);
        double low = timeSeriesPredict.predict(lowArray);
        double average = timeSeriesPredict.predict(averageArray);
        PriceTarget priceTarget = new PriceTarget(close, high, low, average);
        return priceTarget;
    }

    @Override
    public double getVR(String code) {
        double result;
        double AVS = 0.00;
        double BVS = 0.00;
        double CVS = 0.00;
        int stock = Integer.parseInt(code);
        List<DayRecord> list = dayRecordDao.getDayRecordsByCode(stock);
        for (int i = list.size() - 1; i > list.size() - 26; i--) {
            if (Math.abs(list.get(i).getAdjClose() - list.get(i).getOpen()) <= 0.05) {
                CVS += list.get(i).getVolume();
            } else if (list.get(i).getAdjClose() - list.get(i).getOpen() > 0.05) {
                AVS += list.get(i).getVolume();
            } else {
                BVS += list.get(i).getVolume();
            }
        }
        result = (2 * AVS + CVS) / (2 * BVS + CVS);
        return result;
    }

    @Override
    public Map<String, Object> getKDJ(String code) {
        Map<String, Object> map = new HashMap<String, Object>();
        int stock = Integer.parseInt(code);
        List<DayRecord> list = dayRecordDao.getDayRecordsByCode(stock);
        List<String> KList = new ArrayList<>();
        List<String> DList = new ArrayList<>();
        List<String> JList = new ArrayList<>();
        double high = 0.00;
        double low = 0.00;
        double RSV;
        double K;
        double D;
        double J;
        for (int j = 0; j < 7; j++) {
            KList.add("-");
            DList.add("-");
            JList.add("-");
        }
        KList.add("50");
        DList.add("50");
        JList.add("-");
        for (int i = 8; i < list.size(); i++) {
            for (int j = 0; j < 9; j--) {
                if (list.get(i - j).getHigh() > high) {
                    high = list.get(i - j).getHigh();
                }
                if (list.get(i - j).getLow() < low) {
                    low = list.get(i - j).getLow();
                }
            }
            RSV = 100 * (list.get(i).getAdjClose() - low) / (high - low);
            K = 2 * Double.parseDouble(KList.get(i)) / 3 + RSV / 3;
            D = 2 * Double.parseDouble(DList.get(i)) / 3 + K / 3;
            J = 3 * K - 2 * D;
            KList.add(DecimalUtil.RemainTwoDecimal(K));
            DList.add(DecimalUtil.RemainTwoDecimal(D));
            JList.add(DecimalUtil.RemainTwoDecimal(J));
            high = 0.00;
            low = 0.00;
        }
        map.put("K", KList);
        map.put("D", DList);
        map.put("J", JList);

        return map;
    }

    @Override
    public Map<String, Object> getBIAS(String code) {
        Map<String, Object> map = new HashMap<String, Object>();
        int stock = Integer.parseInt(code);
        List<DayRecord> list = dayRecordDao.getDayRecordsByCode(stock);
        List<String> BIAS6 = calculateBIAS(list, 6);
        List<String> BIAS12 = calculateBIAS(list, 12);
        List<String> BIAS24 = calculateBIAS(list, 24);
        map.put("BIAS6", BIAS6);
        map.put("BIAS12", BIAS12);
        map.put("BIAS24", BIAS24);
        return map;
    }

    @Override
    public Map<String, Object> getMACD(String code) {
        Map<String, Object> map = new HashMap<String, Object>();
        int stock = Integer.parseInt(code);
        List<DayRecord> list = dayRecordDao.getDayRecordsByCode(stock);
        List<String> DIFList = new ArrayList<>();
        List<String> DEAList = new ArrayList<>();
        List<String> MACDList = new ArrayList<>();
        double DIF;
        double DEA;
        double MACD;
        double EMA12 = list.get(0).getAdjClose();
        double EMA26 = list.get(0).getAdjClose();
        DIFList.add("-");
        DEAList.add("-");
        MACDList.add("-");
        EMA12 = 11 * EMA12 / 13 + list.get(1).getAdjClose() * 2 / 13;
        EMA26 = 25 * EMA26 / 27 + list.get(1).getAdjClose() * 2 / 27;
        DIF = EMA12 - EMA26;
        DEA = DIF;
        MACD = DIF - DEA;
        DIFList.add(DecimalUtil.RemainTwoDecimal(DIF));
        DEAList.add(DecimalUtil.RemainTwoDecimal(DEA));
        MACDList.add(DecimalUtil.RemainTwoDecimal(MACD));
        for (int i = 2; i < list.size(); i++) {
            EMA12 = 11 * EMA12 / 13 + list.get(i).getAdjClose() * 2 / 13;
            EMA26 = 25 * EMA26 / 27 + list.get(i).getAdjClose() * 2 / 27;
            DIF = EMA12 - EMA26;
            DEA = DEA * 8 / 10 + DIF * 2 / 10;
            MACD = DIF - DEA;
            DIFList.add(DecimalUtil.RemainTwoDecimal(DIF));
            DEAList.add(DecimalUtil.RemainTwoDecimal(DEA));
            MACDList.add(DecimalUtil.RemainTwoDecimal(MACD));
        }

        map.put("DIF", DIFList);
        map.put("DEA", DEAList);
        map.put("MACD", MACDList);
        return map;
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

    private List<String> calculateBIAS(List<DayRecord> list, int dayCount) {
        List<String> result = new ArrayList<String>();
        String s;
        double sum;
        double bias;
        for (int i = 0; i < list.size(); i++) {
            if (i < dayCount) {
                s = "-";
            } else {
                sum = 0.00;
                for (int j = 0; j < dayCount; j++) {
                    sum += list.get(i - j).getAdjClose();
                }
                sum = sum / dayCount;
                bias = 100 * (list.get(i).getAdjClose() - sum) / sum;
                s = DecimalUtil.RemainTwoDecimal(bias);
            }
            result.add(s);
        }
        return result;
    }

}