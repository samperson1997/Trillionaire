package trillionaire.model;

import javax.persistence.Id;

/**
 * Created by michaeltan on 2017/5/14.
 */
public class RealTimeStock {
    private String code; //代码

    private String name; //名称
    private double changepercent; //涨跌幅
    private double trade;  //现价
    private double open; //开盘价
    private double high; //最高价
    private double low; //最低价
    private double settlement; //昨日收盘价
    private long volume; //成交量
    private double turnoverratio; //换手率
    private long amount; //成交量
    private double per; //市盈率
    private double pb; //市净率
    private double mktcap; //总市值
    private double nmc; //流通市值

    public RealTimeStock() {

    }

    public RealTimeStock(String code, String name, double changepercent, double trade, double open, double high, double low, double settlement,
                         long volume, double turnoverratio, long amount, double per, double pb, double mktcap, double nmc){
        this.code = code;
        this.name = name;
        this.changepercent = changepercent;
        this.trade = trade;
        this.open = open;
        this.high = high;
        this.low = low;
        this.settlement = settlement;
        this.volume = volume;
        this.turnoverratio = turnoverratio;
        this.amount = amount;
        this.per = per;
        this.pb = pb;
        this.mktcap = mktcap;
        this.nmc = nmc;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getChangepercent() {
        return changepercent;
    }

    public double getTrade() {
        return trade;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getSettlement() {
        return settlement;
    }

    public long getVolume() {
        return volume;
    }

    public double getTurnoverratio() {
        return turnoverratio;
    }

    public long getAmount() {
        return amount;
    }

    public double getPer() {
        return per;
    }

    public double getPb() {
        return pb;
    }

    public double getMktcap() {
        return mktcap;
    }

    public double getNmc() {
        return nmc;
    }


    public RealTimeStock clone(){

        RealTimeStock cloner = new RealTimeStock(this.code, this.name, this.changepercent, this.trade, this.open, this.high, this.low, this.settlement,
                                    this.volume, this.turnoverratio, this.amount, this.per, this.pb, this.mktcap, this.nmc);

        return cloner;


    }

}
