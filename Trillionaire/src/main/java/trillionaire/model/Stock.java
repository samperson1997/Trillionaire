package trillionaire.model;

import org.hibernate.annotations.Cascade;

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
    private Set<User> users;


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

    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)//使用hibernate注解级联保存和更新
    @JoinTable(name = "user_stock",
            joinColumns = {@JoinColumn(name = "code")},//JoinColumns定义本方在中间表的主键映射
            inverseJoinColumns = {@JoinColumn(name = "id")})
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    @Override
    public boolean equals(Object obj){

        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof Stock)) return false;

        Stock s = (Stock)obj;

        if(s.getCode() == this.code) return true;

        return false;

    }

}
