package trillionaire.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by USER on 2017/5/27.
 */
@Entity
@Table(name = "mean_price")
@IdClass(SimpleKey.class)
public class MeanPrice {

    private int code;
    private Date date;
    private double MA5;
    private double MA10;
    private double MA30;
    private double MA60;
    private double MA120;

    @Id
    @Column(name = "code")
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Id
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "MA5")
    public double getMA5() {
        return MA5;
    }

    public void setMA5(double MA5) {
        this.MA5 = MA5;
    }

    @Column(name = "MA10")
    public double getMA10() {
        return MA10;
    }

    public void setMA10(double MA10) {
        this.MA10 = MA10;
    }

    @Column(name = "MA30")
    public double getMA30() {
        return MA30;
    }

    public void setMA30(double MA30) {
        this.MA30 = MA30;
    }

    @Column(name = "MA60")
    public double getMA60() {
        return MA60;
    }

    public void setMA60(double MA60) {
        this.MA60 = MA60;
    }

    @Column(name = "MA120")
    public double getMA120() {
        return MA120;
    }

    public void setMA120(double MA120) {
        this.MA120 = MA120;
    }


}
