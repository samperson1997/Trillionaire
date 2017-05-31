package trillionaire.model;

import javax.persistence.*;

/**
 * Created by USER on 2017/5/24.
 */
@Entity
@Table(name = "profitability")
@IdClass(CodeYearQuarterKey.class)
public class Profitability {

    private int code;
    private int year;
    private int quarter;

    private double roe;   //净资产收益率
    private double netProfitRatio; //净利率
    private double grossProfitRate;  //毛利率
    private double netProfit;   //净利润(万元)
    private double esp;   //每股收益
    private double income;   //营业收入(百万元)
    private double bips;    //每股主营业务收入(元)

    @Id
    @Column(name = "code")
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Id
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Id
    @Column(name = "quarter")
    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    @Column(name = "roe")
    public double getRoe() {
        return roe;
    }

    public void setRoe(double roe) {
        this.roe = roe;
    }

    @Column(name = "netProfitRatio")
    public double getNetProfitRatio() {
        return netProfitRatio;
    }

    public void setNetProfitRatio(double netProfitRatio) {
        this.netProfitRatio = netProfitRatio;
    }

    @Column(name = "grossProfitRate")
    public double getGrossProfitRate() {
        return grossProfitRate;
    }

    public void setGrossProfitRate(double grossProfitRate) {
        this.grossProfitRate = grossProfitRate;
    }

    @Column(name = "netProfit")
    public double getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(double netProfit) {
        this.netProfit = netProfit;
    }

    @Column(name = "esp")
    public double getEsp() {
        return esp;
    }

    public void setEsp(double esp) {
        this.esp = esp;
    }

    @Column(name = "income")
    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    @Column(name = "bips")
    public double getBips() {
        return bips;
    }

    public void setBips(double bips) {
        this.bips = bips;
    }



}
