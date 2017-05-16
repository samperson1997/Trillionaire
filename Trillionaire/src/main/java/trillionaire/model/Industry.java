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

import org.hibernate.type.TrueFalseType;

@Entity
@Table(name = "industry")
public class Industry {

	private int iid;
	private String name;
	private Set<Stock> stocks;
	
	public Industry(){
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iid")
	public int getIid(){
		return iid;
	}
	
	public void setIid(int iid){
		this.iid = iid;
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
