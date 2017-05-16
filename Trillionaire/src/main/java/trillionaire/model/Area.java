package trillionaire.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "area")
public class Area {

	private int aid;
	private String name;
	private Set<Stock> stocks;
	
	public Area(){
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "aid")
	public int getAid(){
		return aid;
	}
	
	public void setAid(int aid){
		this.aid = aid;
	}
	
	
	@Column(name = "name", unique = true)
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	
	@OneToMany(cascade = CascadeType.MERGE)
	public Set<Stock> getStocks(){
		return stocks;
	}
	
	public void setStocks(Set<Stock> stocks){
		this.stocks = stocks;
	}
	
}
