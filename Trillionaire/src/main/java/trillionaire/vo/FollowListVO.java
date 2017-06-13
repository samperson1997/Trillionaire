package trillionaire.vo;

import trillionaire.model.DayRecord;

/**
 * Created by michaeltan on 2017/6/13.
 */
public class FollowListVO {
    private String name;
    private String code;
    private double open;
    private double high;
    private double low;
    private double close;
    private double adjClose;
    private double change;
    private long volume;
    private double dealSum;
    private double turnoverRate;

    public FollowListVO(String name, String code, DayRecord dayRecord) {
        this.name = name;
        this.code = code;
        this.open = dayRecord.getOpen();
        this.high = dayRecord.getHigh();
        this.low = dayRecord.getLow();
        this.close = dayRecord.getClose();
        this.adjClose = dayRecord.getAdjClose();
        this.change = dayRecord.getChange();
        this.volume = dayRecord.getVolume();
        this.dealSum = dayRecord.getDealSum();
        this.turnoverRate = dayRecord.getTurnoverRate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(double adjClose) {
        this.adjClose = adjClose;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public double getDealSum() {
        return dealSum;
    }

    public void setDealSum(double dealSum) {
        this.dealSum = dealSum;
    }

    public double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }
}
