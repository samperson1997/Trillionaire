package trillionaire.model;

/**
 * Created by michaeltan on 2017/5/16.
 */
public class RecommendationTrends {
    private double strongBuy;
    private double buy;
    private double hold;
    private double underPerform;
    private double shell;

    public RecommendationTrends(double strongBuy, double buy, double hold, double underPerform, double shell) {
        this.strongBuy = strongBuy;
        this.buy = buy;
        this.hold = hold;
        this.underPerform = underPerform;
        this.shell = shell;
    }
}
