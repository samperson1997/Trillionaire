package trillionaire.vo;

/**
 * Created by michaeltan on 2017/5/17.
 */
public class PriceTarget {
    private double close;
    private double high;
    private double low;
    private double average;


    public PriceTarget(double close, double high, double low, double average) {

        this.close = close;
        this.high = high;
        this.low = low;
        this.average = average;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
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

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
