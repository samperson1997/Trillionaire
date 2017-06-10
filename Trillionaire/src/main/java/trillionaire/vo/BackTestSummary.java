package trillionaire.vo;

/**
 * Created by USER on 2017/5/31.
 */
public class BackTestSummary {

    private double backtestReturns;
    private double backtestAnnualizedReturns;
    private double benchReturns;
    private double benchAnnualizedReturns;
    private double alpha;
    private double beta;
    private double sharpe;
    private double sortino;
    private double infoRatio;
    private double volatility;
    private double maxDrawdown;
    private double trackingError;
    private double downsideRisk;


    public double getBacktestReturns() {
        return backtestReturns;
    }

    public void setBacktestReturns(double backtestReturns) {
        this.backtestReturns = backtestReturns;
    }

    public double getBacktestAnnualizedReturns() {
        return backtestAnnualizedReturns;
    }

    public void setBacktestAnnualizedReturns(double backtestAnnualizedReturns) {
        this.backtestAnnualizedReturns = backtestAnnualizedReturns;
    }

    public double getBenchReturns() {
        return benchReturns;
    }

    public void setBenchReturns(double benchReturns) {
        this.benchReturns = benchReturns;
    }

    public double getBenchAnnualizedReturns() {
        return benchAnnualizedReturns;
    }

    public void setBenchAnnualizedReturns(double benchAnnualizedReturns) {
        this.benchAnnualizedReturns = benchAnnualizedReturns;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getSharpe() {
        return sharpe;
    }

    public void setSharpe(double sharpe) {
        this.sharpe = sharpe;
    }

    public double getSortino() {
        return sortino;
    }

    public void setSortino(double sortino) {
        this.sortino = sortino;
    }

    public double getInfoRatio() {
        return infoRatio;
    }

    public void setInfoRatio(double infoRatio) {
        this.infoRatio = infoRatio;
    }

    public double getVolatility() {
        return volatility;
    }

    public void setVolatility(double volatility) {
        this.volatility = volatility;
    }

    public double getMaxDrawdown() {
        return maxDrawdown;
    }

    public void setMaxDrawdown(double maxDrawdown) {
        this.maxDrawdown = maxDrawdown;
    }

    public double getTrackingError() {
        return trackingError;
    }

    public void setTrackingError(double trackingError) {
        this.trackingError = trackingError;
    }

    public double getDownsideRisk() {
        return downsideRisk;
    }

    public void setDownsideRisk(double downsideRisk) {
        this.downsideRisk = downsideRisk;
    }





}
