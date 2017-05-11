package trillionaire.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static java.lang.String.copyValueOf;

/**
 * Created by michaeltan on 2017/5/9.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue

    private int id;
    private String username;
    private String password;
    private String email;

    public User(int id, String name, String password){
        this.id = id;
        this.username = name;
        this.password = password;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
