package trillionaire.vo;

/**
 * Created by michaeltan on 2017/5/16.
 */
public class Earnings {
    private double actual;
    private double estimate;

    public Earnings(double actual, double estimate){
        this.actual = actual;
        this.estimate = estimate;
    }

    public double getActual() {
        return actual;
    }

    public double getEstimate() {
        return estimate;
    }
}
