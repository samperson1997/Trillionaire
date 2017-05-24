package trillionaire.model;

import javax.persistence.*;

import java.util.Set;

import static java.lang.String.copyValueOf;

/**
 * Created by michaeltan on 2017/5/9.
 */
@Entity
@Table(name = "user")
public class User {


    private int id;
    private String username;
    private String password;
    private String email;
    private int isLogin;

    private Set<Stock> preferStocks;

    public User(){

    }

    public User(String email, String name, String password){
        this.email = email;
        this.username = name;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }

    public void setId(int ID) {
        this.id = ID;
    }

    @Column(name = "userName")
    public String getUserName() {
        return this.username;
    }

    public void setUserName(String name) {
        this.username = name;
    }

    @Column(name = "password")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "isLogin")
    public int getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(int isLogin) {
        this.isLogin = isLogin;
    }


    @ManyToMany(cascade = CascadeType.MERGE)
    public Set<Stock> getPreferStocks(){
        return preferStocks;
    }

    public void setPreferStocks(Set<Stock> preferStocks){
        this.preferStocks = preferStocks;
    }
}
