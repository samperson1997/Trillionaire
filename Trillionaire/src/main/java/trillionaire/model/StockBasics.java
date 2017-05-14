package trillionaire.model;

/**
 * Created by michaeltan on 2017/5/14.
 */
public class StockBasics {
    //盈利能力
    private double roe;   //净资产收益率
    private double netProfitRatio; //净利率
    private double grossProfitRate;  //毛利率
    private double netProfit;   //净利润(万元)
    private double esp;   //每股收益
    private double income;   //营业收入(百万元)
    private double bips;    //每股主营业务收入(元)
    //营运能力
    private double arTurnover;  //应收账款周转率(次)
    private double arTurnDays;   //应收账款周转天数(天)
    private double inventoryTurnover; //存货周转率(次)
    private double inventoryDays;   //存货周转天数(天)
    private double currentAssetTurnover; //流动资产周转率(次)
    private double currentAssetDays;   //流动资产周转天数(天)
    //成长能力
    private double mbrg; //主营业务收入增长率(%)
    private double nprg; //净利润增长率(%)
    private double nav; //净资产增长率
    private double targ; //总资产增长率
    private double epsg; //每股收益增长率
    private double seg; //股东权益增长率
    //偿债能力
    private double currentRatio; //流动比率
    private double quickRatio; //速动比率
    private double cashRatio; //现金比率
    private double icRatio; //利息支付倍数
    private double sheqRatio; //股东权益比率
    private double adRatio; //股东权益增长率

    public double getRoe() {
        return roe;
    }

    public double getNetProfitRatio() {
        return netProfitRatio;
    }

    public double getGrossProfitRate() {
        return grossProfitRate;
    }

    public double getNetProfit() {
        return netProfit;
    }

    public double getEsp() {
        return esp;
    }

    public double getIncome() {
        return income;
    }

    public double getBips() {
        return bips;
    }

    public double getArTurnover() {
        return arTurnover;
    }

    public double getArTurnDays() {
        return arTurnDays;
    }

    public double getInventoryTurnover() {
        return inventoryTurnover;
    }

    public double getInventoryDays() {
        return inventoryDays;
    }

    public double getCurrentAssetTurnover() {
        return currentAssetTurnover;
    }

    public double getCurrentAssetDays() {
        return currentAssetDays;
    }

    public double getMbrg() {
        return mbrg;
    }

    public double getNprg() {
        return nprg;
    }

    public double getNav() {
        return nav;
    }

    public double getTarg() {
        return targ;
    }

    public double getEpsg() {
        return epsg;
    }

    public double getSeg() {
        return seg;
    }

    public double getCurrentRatio() {
        return currentRatio;
    }

    public double getQuickRatio() {
        return quickRatio;
    }

    public double getCashRatio() {
        return cashRatio;
    }

    public double getIcRatio() {
        return icRatio;
    }

    public double getSheqRatio() {
        return sheqRatio;
    }

    public double getAdRatio() {
        return adRatio;
    }
}
