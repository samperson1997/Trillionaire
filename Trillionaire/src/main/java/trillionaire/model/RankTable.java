package trillionaire.model;

/**
 * Created by michaeltan on 2017/5/16.
 */
public class RankTable {
    private String name;   //名称
    private double margin;   //涨跌幅
    private int up;   //上涨股票个数
    private int remain;  //持平股票个数
    private int down;  //下跌股票个数
    private String stock;  //股票名称
    private double stockMargin;   //股票涨跌幅

    public RankTable(String name, double margin, int up, int remain, int down, String stock, double stockMargin) {
        this.name = name;
        this.margin = margin;
        this.up = up;
        this.remain = remain;
        this.down = down;
        this.stock = stock;
        this.stockMargin = stockMargin;
    }
}
