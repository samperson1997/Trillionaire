package trillionaire.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "concept")
public class Concept {

	private int cid;
	private String name;
	private Set<Stock> stocks;
	
	public Concept(){
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cid")
	public int getCid(){
		return cid;
	}
	
	public void setCid(int cid){
		this.cid = cid;
	}
	
	
	@Column(name = "name", unique = true)
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	@ManyToMany(cascade = CascadeType.MERGE)
	public Set<Stock> getStocks(){
		return stocks;
	}
	
	public void setStocks(Set<Stock> stocks){
		this.stocks = stocks;
	}
	
	
}

