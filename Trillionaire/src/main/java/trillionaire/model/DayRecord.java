package trillionaire.model;

import java.sql.Date;
import java.time.LocalDate;

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
@Table(name = "day_record")
public class DayRecord {
	
	private Stock stock;
	private LocalDate date;
	private double open;
	private double high;
	private double low;
	private double close;
	private double adjClose;
	private double change;
	private long volume;
	private double dealSum;
	private double turnoverRate;
	
	public DayRecord(){
		
	}
	
	public DayRecord(Stock stock, LocalDate date, double open, double high, double low, double close, double adjClose,
					double change, long volume, double dealSum, double turnoverRate){
		this.stock = stock;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.adjClose = adjClose;
		this.change = change;
		this.volume = volume;
		this.dealSum = dealSum;
		this.turnoverRate = turnoverRate;
	}
	
	@Id
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "code")
	public Stock getStock(){
		return stock;
	}
	
	
	@Id
	@Column(name = "date")
	public LocalDate getDate(){
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
	
	@Column(name = "turnoverRate")
	public double getTurnoverRate(){
		return turnoverRate;
	}
	
	public void setStock(Stock stock){
		this.stock = stock;
	}
	
	
	public void setDate(LocalDate date){
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
	
	public void setTurnoverRate(double turnoverRate){
		this.turnoverRate = turnoverRate;
	}


}
