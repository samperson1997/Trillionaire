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
@Table(name = "member")
public class User {

    @Id
    private String id;
    private String username;
    private String password;
    private String email;

    public User(String email, String name, String password){
        this.email = email;
        this.username = name;
        this.password = password;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String ID) {
        this.id = ID;
    }

    public String getUserName() {
        return this.username;
    }

    public void setUserName(String name) {
        this.username = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
