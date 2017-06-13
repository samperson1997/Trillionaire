package trillionaire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trillionaire.dao.AbilityDao;
import trillionaire.dao.DayRecordDao;
import trillionaire.dao.RealTimeStockDao;
import trillionaire.dao.StockDao;
import trillionaire.model.*;
import trillionaire.service.StockService;
import trillionaire.service.impl.apriori.SimilarStockSelector;
import trillionaire.service.impl.boxjenkins.TimeSeriesPredict;
import trillionaire.util.CodeUtil;
import trillionaire.util.DecimalUtil;
import trillionaire.vo.*;

import java.sql.Date;
import java.util.*;

/**
 * Created by michaeltan on 2017/5/6.
 */
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private DayRecordDao dayRecordDao;
    @Autowired
    private RealTimeStockDao realTimeStockDao;
    @Autowired
    private StockDao stockDao;
    @Autowired
    private AbilityDao abilityDao;
    @Autowired
    private SimilarStockSelector similarStockSelector;

    private TimeSeriesPredict timeSeriesPredict = new TimeSeriesPredict();

    private Map<String, Object> getDailyInfo(String code) {
        Map<String, Object> map = new HashMap<>();
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
        Map<String, Object> map = new HashMap<>();
        int stock = Integer.parseInt(code);
        List<WeekRecord> list = dayRecordDao.getWeekRecordsByCode(stock);
        List<String> ma5 = calculateWeeklyMA(list, 5);
        List<String> ma10 = calculateWeeklyMA(list, 10);
        List<String> ma30 = calculateWeeklyMA(list, 30);
        map.put("candle", list);
        map.put("ma5", ma5);
        map.put("ma10", ma10);
        map.put("ma30", ma30);

        return map;
    }

    private Map<String, Object> getMonthlyInfo(String code) {
        Map<String, Object> map = new HashMap<>();
        int stock = Integer.parseInt(code);
        List<MonthRecord> list = dayRecordDao.getMonthRecordsByCode(stock);
        List<String> ma5 = calculateMonthlyMA(list, 5);
        List<String> ma10 = calculateMonthlyMA(list, 10);
        List<String> ma30 = calculateMonthlyMA(list, 30);
        map.put("candle", list);
        map.put("ma5", ma5);
        map.put("ma10", ma10);
        map.put("ma30", ma30);

        return map;
    }

    @Override
    public Map<String, Object> getStockInfo(String code, String span) {
        Map<String, Object> map;
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
    public RealTimeStock updateRealTime(String code) {
        int stock = Integer.parseInt(code);
        RealTimeStock realTimeStock = realTimeStockDao.getRealTimeByCode(stock);
        System.out.println(realTimeStockDao.getAll().size());
        return realTimeStock;
    }

    @Override
    public List<AssociateStock> associate(String code) {
        List<Stock> list = stockDao.getAllStocks();
        List<AssociateStock> stockList = new ArrayList<>();
        boolean isNum = code.matches("[0-9]+");
        if (isNum) {
            for (int i = 0; i < list.size(); i++) {
                String s = CodeUtil.TransferCode(list.get(i).getCode());
                if (s.startsWith(code)) {
                    AssociateStock associateStock = new AssociateStock(list.get(i).getName(), s);
                    stockList.add(associateStock);
                }
//                if(stockList.size()>5){
//                    break;
//                }
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getName().startsWith(code)) {
                    String s = CodeUtil.TransferCode(list.get(i).getCode());
                    AssociateStock associateStock = new AssociateStock(list.get(i).getName(), s);
                    stockList.add(associateStock);
                }
//                if(stockList.size()>5){
//                    break;
//                }
            }

        }
        Collections.sort(stockList, new Comparator<AssociateStock>() {
            @Override
            public int compare(AssociateStock o1, AssociateStock o2) {
                return new Double(o1.getCode()).compareTo(Double.valueOf(o2.getCode()));
            }
        });
        return stockList;
    }

    @Override
    public Map<String, Object> getSimilarStock(String code) {
        int stock = Integer.parseInt(code);
        List<DayRecord> dayRecordList = dayRecordDao.getDayRecordsByCode(stock);
        String name = dayRecordList.get(0).getStock().getName();
        List<Map<Integer, Object>> list = similarStockSelector.selects(stock);
        List<SimilarStock> associateList = new ArrayList<>();
        List<SimilarStock> associatedList = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).containsKey(1)) {
                associatedList.add((SimilarStock) list.get(i).get(0));
            } else {
                SimilarStock similarStock = (SimilarStock) list.get(i).get(0);
                if (similarStock.getStock1().equals(name)) {
                    associateList.add(similarStock);
                } else {
                    associatedList.add(similarStock);
                }
            }
        }
        Collections.sort(associateList, new Comparator<SimilarStock>() {
            @Override
            public int compare(SimilarStock o1, SimilarStock o2) {
                if (o2.getConfidence() > o1.getConfidence()) {
                    return 1;
                } else {
                    return new Double(o2.getSupport()).compareTo(o1.getSupport());
                }
            }
        });
        Collections.sort(associatedList, new Comparator<SimilarStock>() {
            @Override
            public int compare(SimilarStock o1, SimilarStock o2) {
                if (o2.getConfidence() > o1.getConfidence()) {
                    return 1;
                } else {
                    return new Double(o2.getSupport()).compareTo(o1.getSupport());
                }
            }
        });
        map.put("subject", associateList);
        map.put("object", associatedList);
        return map;
    }

    @Override
    public StockAbility getStockAbility(String code) {
        int stock = Integer.parseInt(code);
        double profit = calculateProfitAbility(stock);
        double operation = calculateOperationAbility(stock);
        double growth = calculateGrowthAbility(stock);
        double debtPaying = calculateDebtPayingAbility(stock);
        if (profit != -1.0 && operation != -1.0 && growth != -1.0 && debtPaying != -1.0) {
            StockAbility stockAbility = new StockAbility(profit, operation, growth, debtPaying);
            return stockAbility;
        } else {
            return null;
        }
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
        int stock = Integer.parseInt(code);
        List<DayRecord> list = dayRecordDao.getDayRecordsByCode(stock);
        if (list.size() < 60) {
            RecommendationTrends recommendationTrends = new RecommendationTrends(0, 0, 0, 0, 0, "");
            return recommendationTrends;
        } else {
            double result;
            double AVS = 0.00;
            double BVS = 0.00;
            double CVS = 0.00;
            for (int i = list.size() - 1; i > list.size() - 32; i--) {
                if (Math.abs(list.get(i).getClose() - list.get(i).getOpen()) <= 0.02) {
                    CVS += list.get(i).getVolume();
                } else if (list.get(i).getClose() - list.get(i).getOpen() > 0.02) {
                    AVS += list.get(i).getVolume();
                } else {
                    BVS += list.get(i).getVolume();
                }
            }
            int strongBuy = 0;
            int buy = 0;
            int hold = 0;
            int sell = 0;
            int strongSell = 0;
            String trends = "";
            for (int i = list.size() - 31; i < list.size(); i++) {
                if (Math.abs(list.get(i).getClose() - list.get(i).getOpen()) <= 0.02) {
                    CVS += list.get(i).getVolume();
                } else if (list.get(i).getClose() - list.get(i).getOpen() > 0.02) {
                    AVS += list.get(i).getVolume();
                } else {
                    BVS += list.get(i).getVolume();
                }
                result = 100 * (2 * AVS + CVS) / (2 * BVS + CVS);
                if (i == list.size() - 1) {
                    if (result > 160 && result < 350) {
                        trends = "减持";
                    } else if (result >= 70 && result <= 160) {
                        trends = "观望";
                    } else if (result <= 35) {
                        trends = "买入";
                    } else if (result > 35 && result < 70) {
                        trends = "增持";
                    } else if (result >= 350) {
                        trends = "卖出";
                    }
                } else {
                    if (result > 160 && result < 400) {
                        sell++;
                    } else if (result >= 70 && result <= 160) {
                        hold++;
                    } else if (result <= 35) {
                        strongBuy++;
                    } else if (result > 35 && result < 70) {
                        buy++;
                    } else if (result >= 400) {
                        strongSell++;
                    }
                }
            }
            RecommendationTrends recommendationTrends = new RecommendationTrends(strongBuy, buy, hold, sell, strongSell, trends);
            return recommendationTrends;
        }
    }

    @Override
    public PriceTarget getPriceTarget(String code) {
        int stockCode = Integer.parseInt(code);
        List<DayRecord> list = dayRecordDao.getDayRecordsByCode(stockCode);
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
        return new PriceTarget(close, high, low, average);
    }

    @Override
    public StockSquare getSquare(String code) {
        double result;
        double AVS = 0.00;
        double BVS = 0.00;
        double CVS = 0.00;
        int stockNum = Integer.parseInt(code);
        List<DayRecord> list = dayRecordDao.getDayRecordsByCode(stockNum);
        Stock stock = list.get(0).getStock();
        for (int i = list.size() - 1; i > list.size() - 33; i--) {
            if (Math.abs(list.get(i).getClose() - list.get(i).getOpen()) <= 0.02) {
                CVS += list.get(i).getVolume();
            } else if (list.get(i).getClose() - list.get(i).getOpen() > 0.02) {
                AVS += list.get(i).getVolume();
            } else {
                BVS += list.get(i).getVolume();
            }
        }
        result = 100 * (2 * AVS + CVS) / (2 * BVS + CVS);
        StockSquare stockSquare = new StockSquare(Double.parseDouble(DecimalUtil.RemainTwoDecimal(result)), stock.getConcepts(), stock.getArea().getName(), stock.getIndustry().getName());

        return stockSquare;
    }

    @Override
    public List<KDJ> getKDJ(String code) {
        int stock = Integer.parseInt(code);
        List<DayRecord> list = dayRecordDao.getDayRecordsByCode(stock);
        List<KDJ> kdj = new ArrayList<>();
        double high = 0.00;
        double low = 0.00;
        double RSV;
        double K;
        double D;
        double J;
        for (int j = 0; j < 7; j++) {
            kdj.add(new KDJ("-", "-", "-", list.get(j).getDate()));
        }
        kdj.add(new KDJ("50.0", "50.0", "-", list.get(7).getDate()));

        for (int i = 8; i < list.size(); i++) {
            for (int j = 0; j < 9; j++) {
                if (list.get(i - j).getHigh() > high) {
                    high = list.get(i - j).getHigh();
                }
                if (list.get(i - j).getLow() < low) {
                    low = list.get(i - j).getLow();
                }
            }
            RSV = 100 * (list.get(i - 1).getAdjClose() - low) / (high - low);
            K = 2 * Double.parseDouble(kdj.get(i - 1).getK()) / 3 + RSV / 3;
            D = 2 * Double.parseDouble(kdj.get(i - 1).getD()) / 3 + K / 3;
            J = 3 * K - 2 * D;
            kdj.add(new KDJ(DecimalUtil.RemainTwoDecimal(K), DecimalUtil.RemainTwoDecimal(D), DecimalUtil.RemainTwoDecimal(J), list.get(i).getDate()));
            high = 0.00;
            low = 0.00;
        }
        return kdj;
    }

    @Override
    public Map<String, Object> getBIAS(String code) {
        Map<String, Object> map = new HashMap<>();
        int stock = Integer.parseInt(code);
        List<DayRecord> list = dayRecordDao.getDayRecordsByCode(stock);
        List<String> BIAS6 = calculateBIAS(list, 6);
        List<String> BIAS12 = calculateBIAS(list, 12);
        List<String> BIAS24 = calculateBIAS(list, 24);
        List<Date> date = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            date.add(list.get(i).getDate());
        }
        map.put("date", date);
        map.put("bias6", BIAS6);
        map.put("bias12", BIAS12);
        map.put("bias24", BIAS24);
        return map;
    }

    @Override
    public Map<String, Object> getMACD(String code) {
        Map<String, Object> map = new HashMap<>();
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
        List<Date> date = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            date.add(list.get(i).getDate());
        }
        map.put("date", date);
        map.put("diff", DIFList);
        map.put("dea", DEAList);
        map.put("macd", MACDList);
        return map;
    }

    @Override
    public Map<String, Object> getMargin(String code1, String code2, String code3) {
        Map<String, Object> map = new HashMap<>();

        List<DayRecord> list0 = dayRecordDao.getDayRecordsByCode(1);
        List<Date> date = new ArrayList<>();
        for (int i = 0; i < list0.size(); i++) {
            date.add(list0.get(i).getDate());
        }
        map.put("date", date);
        map.put("margin1", getSingleMargin(code1));
        map.put("margin2", getSingleMargin(code2));
        map.put("margin3", getSingleMargin(code3));

        return map;
    }

    private List<String> getSingleMargin(String code) {
        List<String> result = new ArrayList<>();
        if (code.equals("000000")) {

        } else {
            int stock = Integer.parseInt(code);
            String s;
            List<DayRecord> list = dayRecordDao.getDayRecordsByCode(stock);
            for (int i = 0; i < list.size(); i++) {
                s = DecimalUtil.RemainTwoDecimal(list.get(i).getChange());
                result.add(s);
            }
        }

        return result;
    }

    private double calculateProfitAbility(int code) {
        double result = 0.0;
        List<Stock> list = stockDao.getAllStocks();
        List<Double> roeList = new ArrayList<>();
        List<Double> netProfitRatioList = new ArrayList<>();
        List<Double> espList = new ArrayList<>();
        List<Double> incomeList = new ArrayList<>();
        List<Double> bipsList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Profitability profitability = abilityDao.getProfitability(list.get(i).getCode());
            if (profitability != null) {
                System.out.println(profitability.getRoe());
                roeList.add(profitability.getRoe());
                netProfitRatioList.add(profitability.getNetProfitRatio());
                espList.add(profitability.getEsp());
                incomeList.add(profitability.getIncome());
                bipsList.add(profitability.getBips());
            }
        }
        Collections.reverse(roeList);
        Collections.reverse(netProfitRatioList);
        Collections.reverse(espList);
        Collections.reverse(incomeList);
        Collections.reverse(bipsList);

        Profitability profitability = abilityDao.getProfitability(code);
        if (profitability == null) {
            return -1.0;
        } else {
            for (int i = 0; i < roeList.size(); i++) {
                if (profitability.getRoe() < roeList.get(i)) {
                    result++;
                }
                if (profitability.getBips() < bipsList.get(i)) {
                    result++;
                }
                if (profitability.getIncome() < incomeList.get(i)) {
                    result++;
                }
                if (profitability.getNetProfitRatio() < netProfitRatioList.get(i)) {
                    result++;
                }
                if (profitability.getEsp() < espList.get(i)) {
                    result++;
                }
            }
            result = result / 6;
            result = 10 - (result * 10 / list.size());
            return result;
        }
    }

    private double calculateOperationAbility(int code) {
        double result = 0.0;
        List<Stock> list = stockDao.getAllStocks();
        List<Double> arTurnoverList = new ArrayList<>();
        List<Double> inventoryTurnoverList = new ArrayList<>();
        List<Double> currentAssetTurnoverList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            OperationAbility operationAbility = abilityDao.getOperationAbility(list.get(i).getCode());
            if (operationAbility != null) {
                arTurnoverList.add(operationAbility.getArTurnover());
                inventoryTurnoverList.add(operationAbility.getInventoryTurnover());
                currentAssetTurnoverList.add(operationAbility.getCurrentAssetTurnover());
            }
        }
        Collections.reverse(arTurnoverList);
        Collections.reverse(inventoryTurnoverList);
        Collections.reverse(currentAssetTurnoverList);

        OperationAbility operationAbility = abilityDao.getOperationAbility(code);

        if (operationAbility == null) {
            return -1;
        } else {
            for (int i = 0; i < arTurnoverList.size(); i++) {
                if (operationAbility.getArTurnover() < arTurnoverList.get(i)) {
                    result++;
                }
                if (operationAbility.getInventoryTurnover() < inventoryTurnoverList.get(i)) {
                    result++;
                }
                if (operationAbility.getCurrentAssetTurnover() < currentAssetTurnoverList.get(i)) {
                    result++;
                }
            }
            result = result / 3;
            result = 10 - (result * 10 / list.size());
            return result;
        }
    }

    private double calculateGrowthAbility(int code) {
        double result = 0.0;
        List<Stock> list = stockDao.getAllStocks();
        List<Double> mbrgList = new ArrayList<>();
        List<Double> nprgList = new ArrayList<>();
        List<Double> navList = new ArrayList<>();
        List<Double> epsgList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            DevelopingAbility developingAbility = abilityDao.DevelopingAbility(list.get(i).getCode());
            if (developingAbility != null) {
                mbrgList.add(developingAbility.getMbrg());
                nprgList.add(developingAbility.getNprg());
                navList.add(developingAbility.getNav());
                epsgList.add(developingAbility.getEpsg());
            }
        }
        Collections.reverse(mbrgList);
        Collections.reverse(nprgList);
        Collections.reverse(navList);
        Collections.reverse(epsgList);

        DevelopingAbility developingAbility = abilityDao.DevelopingAbility(code);
        if (developingAbility == null) {
            return -1.0;
        } else {
            for (int i = 0; i < mbrgList.size(); i++) {
                if (developingAbility.getMbrg() < mbrgList.get(i)) {
                    result++;
                }
                if (developingAbility.getNprg() < nprgList.get(i)) {
                    result++;
                }
                if (developingAbility.getNav() < navList.get(i)) {
                    result++;
                }
                if (developingAbility.getEpsg() < epsgList.get(i)) {
                    result++;
                }
            }
            result = result / 4;
            result = 10 - (result * 10 / list.size());
            return result;
        }
    }

    private double calculateDebtPayingAbility(int code) {
        double result = 0.0;
        List<Stock> list = stockDao.getAllStocks();
        List<Double> currentRatioList = new ArrayList<>();
        List<Double> quickList = new ArrayList<>();
        List<Double> cashList = new ArrayList<>();
        List<Double> sheqList = new ArrayList<>();
        List<Double> adRatioList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            DebtPayingAbility debtPayingAbility = abilityDao.getDebtPayingAbility(list.get(i).getCode());
            if (debtPayingAbility != null) {
                currentRatioList.add(debtPayingAbility.getCurrentRatio());
                quickList.add(debtPayingAbility.getQuickRatio());
                cashList.add(debtPayingAbility.getCashRatio());
                sheqList.add(debtPayingAbility.getSheqRatio());
                adRatioList.add(debtPayingAbility.getAdRatio());
            }
        }
        Collections.reverse(currentRatioList);
        Collections.reverse(quickList);
        Collections.reverse(cashList);
        Collections.reverse(sheqList);
        Collections.reverse(adRatioList);

        DebtPayingAbility debtPayingAbility = abilityDao.getDebtPayingAbility(code);
        if (debtPayingAbility == null) {
            return -1.0;
        } else {
            for (int i = 0; i < currentRatioList.size(); i++) {
                if (debtPayingAbility.getCurrentRatio() < currentRatioList.get(i)) {
                    result++;
                }
                if (debtPayingAbility.getQuickRatio() < quickList.get(i)) {
                    result++;
                }
                if (debtPayingAbility.getCashRatio() < cashList.get(i)) {
                    result++;
                }
                if (debtPayingAbility.getSheqRatio() < sheqList.get(i)) {
                    result++;
                }
                if (debtPayingAbility.getAdRatio() < adRatioList.get(i)) {
                    result++;
                }
            }
            result = result / 5;
            result = 10 - (result * 10 / list.size());
            return result;
        }
    }

    private List<String> calculateMA(List<DayRecord> list, int dayCount) {
        List<String> result = new ArrayList<>();
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

    private List<String> calculateWeeklyMA(List<WeekRecord> list, int dayCount) {
        List<String> result = new ArrayList<>();
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

    private List<String> calculateMonthlyMA(List<MonthRecord> list, int dayCount) {
        List<String> result = new ArrayList<>();
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