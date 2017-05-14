package trillionaire.model;

import javax.persistence.Id;

/**
 * Created by michaeltan on 2017/5/14.
 */
public class RealTimeStock {
    @Id
    private String code; //代码

    private String name; //名称
    private double changepercent; //涨跌幅
    private double trade;  //现价
    private double open; //开盘价
    private double high; //最高价
    private double low; //最低价
    private double settlement; //昨日收盘价
    private int volume; //成交量
    private double turnoverratio; //换手率
    private int amount; //成交量
    private double per; //市盈率
    private double pb; //市净率
    private double mktcap; //总市值
    private double nmc; //流通市值

    public RealTimeStock() {

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

    public int getVolume() {
        return volume;
    }

    public double getTurnoverratio() {
        return turnoverratio;
    }

    public int getAmount() {
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
}
