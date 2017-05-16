package trillionaire.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.sun.org.apache.regexp.internal.recompile;

public class CodeDateKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2591393586600496569L;
	
	private Stock stock;
	private LocalDate date;
	
	public CodeDateKey(){
		
	}
	
	public Stock getStock(){
		return stock;
	}
	
	public void setStock(Stock stock){
		this.stock = stock;
	}
	
	public LocalDate getDate(){
		return date;
	}
	
	public void setDate(LocalDate date){
		this.date = date;
	}
	
	@Override  
    public int hashCode() {  
        final int PRIME = 31;  
        int result = 1;  
        result = PRIME * result + (stock == null ? 0 : stock.hashCode());  
        result = PRIME * result + (date == null ? 0 : date.hashCode());  
        return result;  
    }
	
	@Override
	public boolean equals(Object obj){
		
		if(this == obj) return true;  
        if(obj == null) return false;  
        if(!(obj instanceof CodeDateKey)) return false; 
        
        CodeDateKey codeDateKey = (CodeDateKey)obj;
        if(stock.getCode()==codeDateKey.getStock().getCode() || date.equals(codeDateKey.getDate())) return false;
        
        return true;  
	}

}
