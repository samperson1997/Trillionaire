package trillionaire.vo;

/**
 * Created by michaeltan on 2017/5/17.
 */
public class PriceTarget {
    private double current;
    private double high;
    private double low;
    private double average;


    public PriceTarget(double current, double high, double low, double average) {

        this.current = current;
        this.high = high;
        this.low = low;
        this.average = average;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
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
