package trillionaire.model;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by USER on 2017/6/3.
 */

@Entity
@Table(name = "strategy")
public class Strategy {

    private int sid;
    private String strategyName;
    private String content;
    private Date changingTime;
    private User owner;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Column(name = "strategyName")
    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }


    @Column(name = "content",length = 16777215)
    @Type(type = "text")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "producingTime", updatable = false, insertable = false)
    @Generated(GenerationTime.ALWAYS)
    public Date getChangingTime() {
        return changingTime;
    }

    public void setChangingTime(Date changingTime) {
        this.changingTime = changingTime;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }



}
