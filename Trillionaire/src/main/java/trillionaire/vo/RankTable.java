package trillionaire.vo;

import trillionaire.util.DecimalUtil;

import java.text.DecimalFormat;
import java.util.Map;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMargin() {
        String result = DecimalUtil.TransferToPercent(margin);
        return result;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getStockMargin() {
        String result = DecimalUtil.TransferToPercent(stockMargin);
        return result;
    }

    public void setStockMargin(double stockMargin) {
        this.stockMargin = stockMargin;
    }

}
