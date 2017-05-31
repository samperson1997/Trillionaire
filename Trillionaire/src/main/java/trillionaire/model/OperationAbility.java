package trillionaire.model;

import javax.persistence.*;

/**
 * Created by USER on 2017/5/24.
 */
@Entity
@Table(name = "operation_ability")
@IdClass(CodeYearQuarterKey.class)
public class OperationAbility {

    private int code;
    private int year;
    private int quarter;

    private double arTurnover;  //应收账款周转率(次)
    private double arTurnDays;   //应收账款周转天数(天)
    private double inventoryTurnover; //存货周转率(次)
    private double inventoryDays;   //存货周转天数(天)
    private double currentAssetTurnover; //流动资产周转率(次)
    private double currentAssetDays;   //流动资产周转天数(天)

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

    @Column(name = "arTurnover")
    public double getArTurnover() {
        return arTurnover;
    }

    public void setArTurnover(double arTurnover) {
        this.arTurnover = arTurnover;
    }

    @Column(name = "arTurnDays")
    public double getArTurnDays() {
        return arTurnDays;
    }

    public void setArTurnDays(double arTurnDays) {
        this.arTurnDays = arTurnDays;
    }

    @Column(name = "inventoryTurnover")
    public double getInventoryTurnover() {
        return inventoryTurnover;
    }

    public void setInventoryTurnover(double inventoryTurnover) {
        this.inventoryTurnover = inventoryTurnover;
    }

    @Column(name = "inventoryDays")
    public double getInventoryDays() {
        return inventoryDays;
    }

    public void setInventoryDays(double inventoryDays) {
        this.inventoryDays = inventoryDays;
    }

    @Column(name = "currentAssetTurnover")
    public double getCurrentAssetTurnover() {
        return currentAssetTurnover;
    }

    public void setCurrentAssetTurnover(double currentAssetTurnover) {
        this.currentAssetTurnover = currentAssetTurnover;
    }

    @Column(name = "currentAssetDays")
    public double getCurrentAssetDays() {
        return currentAssetDays;
    }

    public void setCurrentAssetDays(double currentAssetDays) {
        this.currentAssetDays = currentAssetDays;
    }



}
