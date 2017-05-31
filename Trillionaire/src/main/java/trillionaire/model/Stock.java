package trillionaire.model;

import java.util.Set;

import javax.persistence.*;

/**
 * Created by michaeltan on 2017/5/6.
 */
@Entity
@Table(name = "stock")
public class Stock {

    private int code;
    private String name;
    private String market;
    private Area area;
    private Industry industry;

    private Set<Concept> concepts;

    private Set<DayRecord> dayRecords;


    public Stock() {

    }

    public Stock(int code, String name, String market){
        this.code = code;
        this.name = name;
        this.market = market;
    }

    @Id
    @Column(name = "code")
    public int getCode(){

        return code;
    }

    public void setCode(int code){

        this.code = code;
    }

    @Column(name = "name")
    public String getName(){

        return name;
    }

    public void setName(String name){

        this.name = name;
    }

    @Column(name = "market")
    public String getMarket(){

        return market;
    }

    public void setMarket(String market){

        this.market = market;

    }



    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "aid")
    public Area getArea(){
        return area;
    }

    public void setArea(Area area){
        this.area = area;
    }

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "iid")
    public Industry getIndustry(){
        return industry;
    }

    public void setIndustry(Industry industry){
        this.industry = industry;
    }

    @OneToMany(cascade = CascadeType.MERGE)
    @OrderBy("date ASC")
    public Set<DayRecord> getDayRecords(){
        return dayRecords;
    }

    public void setDayRecords(Set<DayRecord> dayRecords){
        this.dayRecords = dayRecords;
    }

    @ManyToMany(cascade = CascadeType.MERGE)
    public Set<Concept> getConcepts(){
        return this.concepts;
    }

    public void setConcepts(Set<Concept> concepts){
        this.concepts = concepts;
    }

}
