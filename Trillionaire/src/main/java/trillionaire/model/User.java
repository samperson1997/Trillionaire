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

    public User(String id, String name, String password){
        this.id = id;
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

}
