package trillionaire.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.Set;

import static java.lang.String.copyValueOf;

/**
 * Created by michaeltan on 2017/5/9.
 */

@Entity
@Table(name = "user")
public class User {

    private int id;
    private String password;
    private String email;
    private Set<Stock> concernedStocks;
    private Set<Strategy> strategies;

    public User(){

    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return this.id;
    }

    public void setId(int ID) {
        this.id = ID;
    }

    @Column(name = "password")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)//使用hibernate注解级联保存和更新
    @JoinTable(name = "user_stock",
            joinColumns = {@JoinColumn(name = "id")},//JoinColumns定义本方在中间表的主键映射
            inverseJoinColumns = {@JoinColumn(name = "code")})
    public Set<Stock> getConcernedStocks() {
        return concernedStocks;
    }

    public void setConcernedStocks(Set<Stock> concernedStocks) {
        this.concernedStocks = concernedStocks;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    public Set<Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(Set<Strategy> strategies) {
        this.strategies = strategies;
    }
}
