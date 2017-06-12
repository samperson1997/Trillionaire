package trillionaire.vo;

/**
 * Created by michaeltan on 2017/5/16.
 */
public class RecommendationTrends {
    private int buy;
    private int hold;
    private int sell;

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

    public int getHold() {
        return hold;
    }

    public void setHold(int hold) {
        this.hold = hold;
    }

    public int getSell() {
        return sell;
    }

    public void setSell(int sell) {
        this.sell = sell;
    }

    public RecommendationTrends(int buy, int hold, int sell) {

        this.buy = buy;
        this.hold = hold;
        this.sell = sell;
    }
}
