package trillionaire.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(CodeDateKey.class)
@Table(name = "month_record")
@JsonIgnoreProperties(value = {"stock"})
public class MonthRecord {

    private Stock stock;
    private Date date;
    private double open;
    private double high;
    private double low;
    private double close;
    private double adjClose;
    private double change;
    private long volume;
    private double dealSum;


    public MonthRecord(){

    }



    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "code")
    public Stock getStock(){
        return stock;
    }


    @Id
    @Column(name = "date")
    public Date getDate(){
        return date;
    }

    @Column(name = "open")
    public double getOpen(){
        return open;
    }

    @Column(name = "high")
    public double getHigh(){
        return high;
    }

    @Column(name = "low")
    public double getLow(){
        return low;
    }

    @Column(name = "close")
    public double getClose(){
        return close;
    }

    @Column(name = "adjClose")
    public double getAdjClose(){
        return adjClose;
    }

    @Column(name = "changesss")
    public double getChange(){
        return change;
    }

    @Column(name = "volume")
    public long getVolume(){
        return volume;
    }

    @Column(name = "dealSum")
    public double getDealSum(){
        return dealSum;
    }

    public void setStock(Stock stock){
        this.stock = stock;
    }


    public void setDate(Date date){
        this.date = date;
    }

    public void setOpen(double open){
        this.open = open;
    }

    public void setHigh(double high){
        this.high = high;
    }

    public void setLow(double low){
        this.low = low;
    }

    public void setClose(double close){
        this.close = close;
    }

    public void setAdjClose(double adjClose){
        this.adjClose = adjClose;
    }

    public void setChange(double change){
        this.change = change;
    }

    public void setVolume(long volume){
        this.volume = volume;
    }

    public void setDealSum(double dealSum){
        this.dealSum = dealSum;
    }



}
