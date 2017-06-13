package trillionaire.vo;

/**
 * Created by michaeltan on 2017/5/16.
 */
public class RecommendationTrends {
    private int strongBuy;
    private int buy;
    private int hold;
    private int sell;
    private int strongSell;
    private String trends;

    public RecommendationTrends(int strongBuy, int buy, int hold, int sell, int strongSell, String trends) {
        this.strongBuy = strongBuy;
        this.buy = buy;
        this.hold = hold;
        this.sell = sell;
        this.strongSell = strongSell;
        this.trends = trends;
    }

    public String getTrends() {
        return trends;
    }

    public void setTrends(String trends) {
        this.trends = trends;
    }

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

    public int getStrongBuy() {
        return strongBuy;
    }

    public void setStrongBuy(int strongBuy) {
        this.strongBuy = strongBuy;
    }

    public int getStrongSell() {
        return strongSell;
    }

    public void setStrongSell(int strongSell) {
        this.strongSell = strongSell;
    }
}
