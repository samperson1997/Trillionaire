package trillionaire.model;

import javax.persistence.*;

/**
 * Created by USER on 2017/5/24.
 */


@Entity
@Table(name = "debt_paying_ability")
@IdClass(CodeYearQuarterKey.class)
public class DebtPayingAbility {

    private int code;
    private int year;
    private int quarter;

    private double currentRatio; //流动比率
    private double quickRatio; //速动比率
    private double cashRatio; //现金比率
    private double icRatio; //利息支付倍数
    private double sheqRatio; //股东权益比率
    private double adRatio; //股东权益增长率

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

    @Column(name = "currentRatio")
    public double getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(double currentRatio) {
        this.currentRatio = currentRatio;
    }

    @Column(name = "quickRatio")
    public double getQuickRatio() {
        return quickRatio;
    }

    public void setQuickRatio(double quickRatio) {
        this.quickRatio = quickRatio;
    }

    @Column(name = "cashRatio")
    public double getCashRatio() {
        return cashRatio;
    }

    public void setCashRatio(double cashRatio) {
        this.cashRatio = cashRatio;
    }

    @Column(name = "icRatio")
    public double getIcRatio() {
        return icRatio;
    }

    public void setIcRatio(double icRatio) {
        this.icRatio = icRatio;
    }

    @Column(name = "sheqRatio")
    public double getSheqRatio() {
        return sheqRatio;
    }

    public void setSheqRatio(double sheqRatio) {
        this.sheqRatio = sheqRatio;
    }

    @Column(name = "adRatio")
    public double getAdRatio() {
        return adRatio;
    }

    public void setAdRatio(double adRatio) {
        this.adRatio = adRatio;
    }



}
