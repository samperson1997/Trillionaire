package trillionaire.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by michaeltan on 2017/5/6.
 */
@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue
    private double open;
    private double high;
    private double low;
    private double close;
    private int volume; //成交量
    private int turnover; //成交额
    private double ma;

    public Stock() {

    }

    public void setOpen(double open) {
        this.open = open;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setTurnover(int turnover) {
        this.turnover = turnover;
    }

    public void setMa(double ma) {
        this.ma = ma;
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

    public double getClose() {
        return close;
    }

    public int getVolume() {
        return volume;
    }

    public int getTurnover() {
        return turnover;
    }

    public double getMa() {
        return ma;
    }
}
